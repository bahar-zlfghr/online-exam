package ir.ac.alzahra.onlineexam.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class MainController {

    @GetMapping("/")
    public ModelAndView viewIndexPage(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
