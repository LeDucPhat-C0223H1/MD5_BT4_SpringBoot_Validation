package ra.springboot_validation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.springboot_validation.Dto.request.SignInDto;
import ra.springboot_validation.Dto.request.SignUpDto;
import ra.springboot_validation.Dto.response.UserResponse;
import ra.springboot_validation.exception.UserNameAndPasswordException;
import ra.springboot_validation.service.IUserService;


@RestController
@RequestMapping("api.authentication.com/v1")
public class AuthController {
    @Autowired
    private IUserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<UserResponse> signin(@Valid @RequestBody SignInDto signInDto) throws UserNameAndPasswordException {
        return new ResponseEntity<>(userService.login(signInDto), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signin(@Valid @ModelAttribute SignUpDto signUpDto) {
        userService.register(signUpDto);
        return new ResponseEntity<>("Đăng kí thành công", HttpStatus.CREATED);
    }
}
