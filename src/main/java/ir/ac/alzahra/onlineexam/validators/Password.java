package ir.ac.alzahra.onlineexam.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author : Bahar Zolfaghari
 **/
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({
        ElementType.FIELD,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "{password.well-formed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
