package ra.springboot_validation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

}
