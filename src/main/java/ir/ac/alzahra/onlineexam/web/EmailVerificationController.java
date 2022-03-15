package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.model.data.ConfirmationToken;
import ir.ac.alzahra.onlineexam.model.data.EducationalUser;
import ir.ac.alzahra.onlineexam.service.educationaluser.EducationalUserService;
import ir.ac.alzahra.onlineexam.service.token.ConfirmationTokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class EmailVerificationController {
    private final ConfirmationTokenService confirmationTokenService;
    private final EducationalUserService educationalUserService;

    public EmailVerificationController(ConfirmationTokenService confirmationTokenService, EducationalUserService educationalUserService) {
        this.confirmationTokenService = confirmationTokenService;
        this.educationalUserService = educationalUserService;
    }

    @GetMapping(value = "/confirm-email")
    @PostMapping(value = "/confirm-email")
    public ModelAndView confirmUserAccount(@RequestParam("token") String token, ModelAndView modelAndView) {
        Optional<ConfirmationToken> byTokenAndClickable = confirmationTokenService.findByTokenAndClickable(token, Boolean.TRUE);
        if (byTokenAndClickable.isPresent()) {
            ConfirmationToken confirmationToken = byTokenAndClickable.get();
            confirmationTokenService.update(confirmationToken, Boolean.FALSE);
            EducationalUser educationalUser = confirmationToken.getEducationalUser();
            educationalUser.setEmailVerification(Boolean.TRUE);
            educationalUserService.updateEmailVerification(educationalUser.getId(), educationalUser.isEmailVerification());
            modelAndView.addObject("emailVerificationError", Boolean.FALSE);
            modelAndView.addObject("email", educationalUser.getEmail());
        }
        else {
            modelAndView.addObject("emailVerificationError", Boolean.TRUE);
        }
        modelAndView.setViewName("email-verified");
        return modelAndView;
    }
}
