package ra.springboot_validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ra.springboot_validation.service.IUserService;

@Controller
@RequiredArgsConstructor
public class UserNameValidator implements ConstraintValidator<UserNameUnique, String> {
    private final IUserService userService;

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existsByUserName(userName);
    }
}
