package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.model.data.Role;
import ir.ac.alzahra.onlineexam.model.data.User;
import ir.ac.alzahra.onlineexam.service.MessageSourceComponent;
import ir.ac.alzahra.onlineexam.service.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class CommonController {
    private final MessageSourceComponent messageSourceComponent;

    public CommonController(MessageSourceComponent messageSourceComponent) {
        this.messageSourceComponent = messageSourceComponent;
    }

    @PostMapping("/common-dashboard")
    public ModelAndView viewCommonDashboardPage(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:" + messageSourceComponent.getMessage("online.exam.base-url") + "/common-dashboard");
        return modelAndView;
    }

    @GetMapping("/common-dashboard")
    public ModelAndView getCommonDashboardPage(ModelAndView modelAndView) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
        }
        else if (principal instanceof UserPrincipal) {
            User user = ((UserPrincipal) principal).getUser();
            modelAndView.setViewName("common/common-dashboard");
            modelAndView.addObject("user", user);
            modelAndView.addObject("userRoles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        }
        return modelAndView;
    }
}
