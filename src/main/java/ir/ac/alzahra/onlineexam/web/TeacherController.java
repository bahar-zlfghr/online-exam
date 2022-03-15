package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.model.data.Teacher;
import ir.ac.alzahra.onlineexam.service.teacher.TeacherService;
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
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teacher-dashboard/{id}")
    public ModelAndView getTeacherDashboard(@ModelAttribute Teacher teacher, @PathVariable Long id, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }
        modelAndView.setViewName("teacher/teacher-dashboard");
        modelAndView.addObject("teacher", teacherService.findById(id));
        modelAndView.addObject("teacherId", id);
        session.setAttribute("teacherId", id);
        return modelAndView;
    }
}
