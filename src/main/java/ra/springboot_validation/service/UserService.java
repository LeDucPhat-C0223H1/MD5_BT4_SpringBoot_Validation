package ra.springboot_validation.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ra.springboot_validation.Dto.request.SignInDto;
import ra.springboot_validation.Dto.request.SignUpDto;
import ra.springboot_validation.Dto.response.UserResponse;
import ra.springboot_validation.entity.Role;
import ra.springboot_validation.entity.RoleName;
import ra.springboot_validation.entity.User;
import ra.springboot_validation.exception.UserNameAndPasswordException;
import ra.springboot_validation.repository.IRoleRepository;
import ra.springboot_validation.repository.IUserRepopsitory;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final IUserRepopsitory userRepopsitory;
    private final ModelMapper modelMapper;
    private final UploadService uploadService;
    private final IRoleRepository roleRepository;
    @Override
    public UserResponse login(SignInDto signInDto) throws UserNameAndPasswordException {
        User userLogin = userRepopsitory
                .findByUserNameAndPassword(signInDto.getUserName(), signInDto.getPassword())
                .orElseThrow(() -> new UserNameAndPasswordException("Tài khoản hoặc mật khẩu không đúng!"));
        return modelMapper.map(userLogin, UserResponse.class);
    }

    @Override
    public void register(SignUpDto signUpDto) {
        // upload file nếu có
        String avatar = "https://iphonecugiare.com/wp-content/uploads/2020/03/84156601_1148106832202066_479016465572298752_o.jpg";
        if(!signUpDto.getFile().isEmpty()){
            // uploadfile
            avatar = uploadService.uploadFileToServer(signUpDto.getFile());
        }
        // danh sách quyền
        Set<Role> roleSet = new HashSet<>();
        if (signUpDto.getListRoles() == null || signUpDto.getListRoles().isEmpty()) {
            roleSet.add(roleRepository.findByRoleName(RoleName.USER).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
        } else {
            signUpDto.getListRoles().forEach(s -> {
                switch (s) {
                    case "admin":
                        roleSet.add(roleRepository.findByRoleName(RoleName.ADMIN).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
                    case "pm":
                        roleSet.add(roleRepository.findByRoleName(RoleName.PM).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
                    case "leader":
                        roleSet.add(roleRepository.findByRoleName(RoleName.LEADER).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
                    case "user":
                    default:
                        roleSet.add(roleRepository.findByRoleName(RoleName.USER).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));

                }
            });
        }
        User user = modelMapper.map(signUpDto, User.class);
        user.setAvatar(avatar);
        user.setRoles(roleSet);
        userRepopsitory.save(user);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepopsitory.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepopsitory.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepopsitory.existsByPhone(phone);
    }
}
