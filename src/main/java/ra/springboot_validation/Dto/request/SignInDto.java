package ra.springboot_validation.Dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    @NotBlank(message = "Không được để trống!")
    private String userName;
    @NotBlank(message = "Không được để trống!")
    private String password;


}
