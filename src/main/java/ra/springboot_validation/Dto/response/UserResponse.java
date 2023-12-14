package ra.springboot_validation.Dto.response;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String userName;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String email;

    private String phone;

    private String avatar;

    private  Boolean status;

    private Set<String> roles;
}
