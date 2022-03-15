package ir.ac.alzahra.onlineexam.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class MessageSourceComponent {
    private final MessageSource messageSource;

    public MessageSourceComponent(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, String... args) {
        return messageSource.getMessage(key, args, new Locale("en"));
    }
}
