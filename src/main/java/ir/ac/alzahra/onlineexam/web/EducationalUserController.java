package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.dto.EducationalUserDto;
import ir.ac.alzahra.onlineexam.model.data.EducationalUser;
import ir.ac.alzahra.onlineexam.service.course.CourseService;
import ir.ac.alzahra.onlineexam.service.educationaluser.EducationalUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class EducationalUserController {
    private final EducationalUserService educationalUserService;
    private final CourseService courseService;

    public EducationalUserController(EducationalUserService educationalUserService, CourseService courseService) {
        this.educationalUserService = educationalUserService;
        this.courseService = courseService;
    }

    @GetMapping("/educational-users")
    public Set<EducationalUser> getEducationalUsers() {
        return educationalUserService.findAll();
    }

    @GetMapping("/educational-user/confirm/{educationalUserId}")
    public ModelAndView confirmEducationalUser(@PathVariable Long educationalUserId, ModelAndView modelAndView) {
        educationalUserService.updateAdminVerification(educationalUserId, Boolean.TRUE);
        modelAndView.setViewName("admin/admin-dashboard");
        return modelAndView;
    }

    @GetMapping(value = "/educational-user/search")
    public Set<EducationalUser> searchEducationalUsers(@RequestParam(required = false, name = "firstName") String firstName,
                                                       @RequestParam(required = false, name = "lastName") String lastName,
                                                       @RequestParam(required = false, name = "email") String email,
                                                       @RequestParam(required = false, name = "role") String role) {
        return educationalUserService.findAll(firstName, lastName, email, role);
    }

    @GetMapping("/educational-user/{educationalUserId}/add-to-course")
    public ModelAndView getPageAddEducationalUserToCourse(@PathVariable Long educationalUserId, ModelAndView modelAndView, HttpSession session) {
        session.setAttribute("educationalUser", educationalUserService.findById(educationalUserId));
        modelAndView.setViewName("course/add-educational-user-to-course");
        modelAndView.addObject("educationalUserDto", new EducationalUserDto());
        modelAndView.addObject("courses", courseService.findAll());
        return modelAndView;
    }

    @PostMapping("/educational-user/add-to-course")
    public ModelAndView AddEducationalUserToCourse(@ModelAttribute EducationalUserDto educationalUserDto, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }

        EducationalUser educationalUser = (EducationalUser) session.getAttribute("educationalUser");
        session.removeAttribute("educationalUser");
        educationalUser.setCourses(educationalUserDto.getCourses());
        educationalUserService.updateAdminCourses(educationalUser);
        modelAndView.setViewName("redirect:http://localhost:8080/admin-dashboard/" + session.getAttribute("adminId"));
        return modelAndView;
    }
}
