package miu.edu.auction.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = miu.edu.auction.validator.EmailUniqueValidator.class)
public @interface EmailUnique {

    String message() default "{user.email.validation.message}";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

}
