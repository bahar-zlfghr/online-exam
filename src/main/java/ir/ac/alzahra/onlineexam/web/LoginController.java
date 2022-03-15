package ir.ac.alzahra.onlineexam.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class LoginController {

    @GetMapping("/perform-login")
    @PostMapping("/perform-login")
    public ModelAndView viewLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/login-error")
    public ModelAndView viewLoginErrorPage(ModelAndView modelAndView) {
        modelAndView.addObject("loginError", true);
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
