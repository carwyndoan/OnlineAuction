package miu.edu.auction.validator;

import miu.edu.auction.domain.User;
import miu.edu.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(EmailUnique arg0) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = null;
        try {
            user = userService.findUserByEmail(value);
        } catch (Exception e) {
            System.out.println("Couldn't find product...");
        }
        return user == null ? true : false;
    }
}
