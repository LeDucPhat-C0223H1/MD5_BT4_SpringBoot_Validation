package ra.springboot_validation.service;

import ra.springboot_validation.Dto.request.SignInDto;
import ra.springboot_validation.Dto.request.SignUpDto;
import ra.springboot_validation.Dto.response.UserResponse;
import ra.springboot_validation.exception.UserNameAndPasswordException;

public interface IUserService {
    UserResponse login(SignInDto signInDto) throws UserNameAndPasswordException;
    void register(SignUpDto signUpDto);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
