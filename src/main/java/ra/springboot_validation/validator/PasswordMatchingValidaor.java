package ra.springboot_validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchingValidaor implements ConstraintValidator<PasswordMatching, Object> {
    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordMatching passwordMatching) {
        this.password = passwordMatching.password();
        this.confirmPassword = passwordMatching.confirmPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

        return (passwordValue != null) ? passwordValue.equals(confirmPasswordValue) : confirmPasswordValue == null;
    }


}
