package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.model.data.Student;
import ir.ac.alzahra.onlineexam.service.student.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student-dashboard/{id}")
    public ModelAndView getStudentDashboard(@ModelAttribute Student student, @PathVariable Long id, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }
        modelAndView.setViewName("student/student-dashboard");
        modelAndView.addObject("student", studentService.findById(id));
        modelAndView.addObject("studentId", id);
        session.setAttribute("studentId", id);
        return modelAndView;
    }
}
