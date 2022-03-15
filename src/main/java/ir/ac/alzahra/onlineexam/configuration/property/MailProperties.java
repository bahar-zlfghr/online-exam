package ir.ac.alzahra.onlineexam.configuration.property;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class MailProperties {
    private final Environment environment;

    public MailProperties(Environment environment) {
        this.environment = environment;
    }

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String protocol;
    private String auth;
    private String starttlsEnable;
    private String debug;

    @PostConstruct
    public void afterPropertiesSet() {
        host = environment.getRequiredProperty("mail.host");
        port = Integer.valueOf(environment.getRequiredProperty("mail.port"));
        username = environment.getRequiredProperty("mail.username");
        password = decodePassword();
        protocol = environment.getRequiredProperty("mail.protocol");
        auth = environment.getRequiredProperty("mail.smtp.auth");
        starttlsEnable = environment.getRequiredProperty("mail.smtp.starttls.enable");
        debug = environment.getRequiredProperty("mail.debug");
    }

    private String decodePassword() {
        String pass = environment.getRequiredProperty("mail.password");
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(pass));
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getAuth() {
        return auth;
    }

    public String getStarttlsEnable() {
        return starttlsEnable;
    }

    public String getDebug() {
        return debug;
    }
}
