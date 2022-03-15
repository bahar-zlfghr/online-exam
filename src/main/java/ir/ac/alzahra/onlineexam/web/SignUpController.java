package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.dto.UserDto;
import ir.ac.alzahra.onlineexam.model.data.*;
import ir.ac.alzahra.onlineexam.service.MessageSourceComponent;
import ir.ac.alzahra.onlineexam.service.mail.EmailSenderService;
import ir.ac.alzahra.onlineexam.service.role.RoleServiceImpl;
import ir.ac.alzahra.onlineexam.service.student.StudentService;
import ir.ac.alzahra.onlineexam.service.teacher.TeacherService;
import ir.ac.alzahra.onlineexam.service.token.ConfirmationTokenService;
import ir.ac.alzahra.onlineexam.service.user.UserService;
import ir.ac.alzahra.onlineexam.service.util.MailGenerator;
import ir.ac.alzahra.onlineexam.service.util.TokenGenerator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class SignUpController {
    private final RoleServiceImpl roleService;
    private final UserService userService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;
    private final TokenGenerator tokenGenerator;
    private final MailGenerator mailGenerator;
    private final MessageSourceComponent messageSourceComponent;

    public SignUpController(RoleServiceImpl roleService, UserService userService, TeacherService teacherService, StudentService studentService,
                            ConfirmationTokenService confirmationTokenService, EmailSenderService emailSenderService,
                            TokenGenerator tokenGenerator, MailGenerator mailGenerator, MessageSourceComponent messageSourceComponent) {
        this.roleService = roleService;
        this.userService = userService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSenderService = emailSenderService;
        this.tokenGenerator = tokenGenerator;
        this.mailGenerator = mailGenerator;
        this.messageSourceComponent = messageSourceComponent;
    }

    @GetMapping("/perform-sign-up")
    public ModelAndView viewSignUpPage(ModelAndView modelAndView) {
        modelAndView.setViewName("sign-up");
        modelAndView.addObject("userDto", new UserDto());
        modelAndView.addObject("roles", getRoles());
        return modelAndView;
    }

    @PostMapping("/perform-sign-up")
    public ModelAndView register(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("sign-up");
            modelAndView.addObject("roles", getRoles());
            return modelAndView;
        }

        Optional<User> byEmail = userService.findByEmail(userDto.getEmail());
        if (byEmail.isPresent()) {
            modelAndView.addObject("duplicateEmailError", Boolean.TRUE);
            modelAndView.addObject("email", userDto.getEmail());
            modelAndView.setViewName("sign-up");
        }
        else {
            Role role = roleService.findById(userDto.getRole()).orElse(null);
            ConfirmationToken confirmationToken = prepareConfirmationToken();

            if (Objects.requireNonNull(role).getName().equals(messageSourceComponent.getMessage("role.teacher"))) {
                Teacher teacher = prepareTeacher(userDto, role);
                teacherService.save(teacher);

                confirmationToken.setEducationalUser(teacher);
                confirmationTokenService.save(confirmationToken);

                emailSenderService.sendEmail(prepareMailMessage(teacher, confirmationToken));

                modelAndView.addObject("email", teacher.getEmail());
                modelAndView.addObject("user", teacher);
                modelAndView.addObject("userRoles", getRolesName(teacher.getRoles()));
            } else if (Objects.requireNonNull(role).getName().equals(messageSourceComponent.getMessage("role.student"))) {
                Student student = prepareStudent(userDto, role);
                studentService.save(student);

                confirmationToken.setEducationalUser(student);
                confirmationTokenService.save(confirmationToken);

                emailSenderService.sendEmail(prepareMailMessage(student, confirmationToken));

                modelAndView.addObject("email", student.getEmail());
                modelAndView.addObject("user", student);
                modelAndView.addObject("userRoles", getRolesName(student.getRoles()));
            }

            modelAndView.addObject("emailSended", Boolean.TRUE);
            modelAndView.setViewName("common/common-dashboard");
        }
        return modelAndView;
    }

    private Set<Role> getRoles() {
        Set<Role> roles = roleService.findAll();
        roles.removeIf(role -> role.getName().equals(messageSourceComponent.getMessage("role.admin")));
        return roles;
    }

    private Teacher prepareTeacher(UserDto userDto, Role role) {
        return (Teacher) new Teacher()
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword())
                .addRole(role);
    }

    private Student prepareStudent(UserDto userDto, Role role) {
        return  (Student) new Student()
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword())
                .addRole(role);
    }

    private ConfirmationToken prepareConfirmationToken() {
        return new ConfirmationToken()
                .setToken(tokenGenerator.generateRandomToken())
                .setCreatedDate(new Date())
                .setClickable(Boolean.TRUE);
    }

    private SimpleMailMessage prepareMailMessage(EducationalUser educationalUser, ConfirmationToken confirmationToken) {
        return mailGenerator.generateEmail(educationalUser, confirmationToken);
    }

    private List<String> getRolesName(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }
}
