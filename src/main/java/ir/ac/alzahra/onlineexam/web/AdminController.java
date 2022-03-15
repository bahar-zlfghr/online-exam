package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.model.data.User;
import ir.ac.alzahra.onlineexam.service.admin.AdminService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin-dashboard/{id}")
    public ModelAndView getAdminDashboard(@ModelAttribute User user, @PathVariable Long id, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }

        modelAndView.setViewName("admin/admin-dashboard");
        modelAndView.addObject("admin", adminService.findById(id));
        session.setAttribute("adminId", id);
        return modelAndView;
    }
}
