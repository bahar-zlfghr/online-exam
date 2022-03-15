package ir.ac.alzahra.onlineexam.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author : Bahar Zolfaghari
 **/
@Configuration
@ComponentScan(value = "ir.ac.alzahra.onlineexam")
@PropertySource({"classpath:messages.properties", "classpath:mail.properties"})
public class ApplicationConfiguration {

    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
