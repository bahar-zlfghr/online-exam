package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.service.MessageSourceComponent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Bahar Zolfaghari
 **/
@Controller
public class ErrorController {
    private final MessageSourceComponent messageSourceComponent;

    public ErrorController(MessageSourceComponent messageSourceComponent) {
        this.messageSourceComponent = messageSourceComponent;
    }

    @GetMapping("/404")
    public ModelAndView handleError(ModelAndView modelAndView) {
        modelAndView.setViewName("404-error");
        modelAndView.addObject("error404", messageSourceComponent.getMessage("error.404"));
        return modelAndView;
    }
}
