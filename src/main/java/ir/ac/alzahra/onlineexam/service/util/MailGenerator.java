package ir.ac.alzahra.onlineexam.service.util;

import ir.ac.alzahra.onlineexam.model.data.ConfirmationToken;
import ir.ac.alzahra.onlineexam.model.data.EducationalUser;
import ir.ac.alzahra.onlineexam.service.MessageSourceComponent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class MailGenerator {
    private final MessageSourceComponent messageSourceComponent;

    public MailGenerator(MessageSourceComponent messageSourceComponent) {
        this.messageSourceComponent = messageSourceComponent;
    }


    public SimpleMailMessage generateEmail(EducationalUser educationalUser, ConfirmationToken confirmationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(educationalUser.getEmail());
        mailMessage.setSubject(messageSourceComponent.getMessage("mail.subject"));
        mailMessage.setFrom(messageSourceComponent.getMessage("mail.from"));
        mailMessage.setText(
                messageSourceComponent.getMessage("mail.text",
                        messageSourceComponent.getMessage("token", confirmationToken.getToken())));
        return mailMessage;
    }
}
