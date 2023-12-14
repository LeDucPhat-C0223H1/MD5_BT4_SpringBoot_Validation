package ra.springboot_validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.springboot_validation.entity.Role;
import ra.springboot_validation.entity.RoleName;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName roleName);

}
