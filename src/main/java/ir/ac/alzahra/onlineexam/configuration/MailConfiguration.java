package ir.ac.alzahra.onlineexam.configuration;

import ir.ac.alzahra.onlineexam.configuration.property.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author : Bahar Zolfaghari
 **/
@Configuration
public class MailConfiguration {
    private final MailProperties mailProperties;

    public MailConfiguration(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        mailSender.getJavaMailProperties().put("mail.protocol", mailProperties.getProtocol());
        mailSender.getJavaMailProperties().put("mail.smtp.auth", mailProperties.getAuth());
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", mailProperties.getStarttlsEnable());
        mailSender.getJavaMailProperties().put("mail.debug", mailProperties.getDebug());
        return mailSender;
    }
}
