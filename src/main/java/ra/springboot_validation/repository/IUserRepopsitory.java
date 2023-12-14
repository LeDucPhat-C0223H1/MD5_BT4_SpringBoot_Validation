package ra.springboot_validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.springboot_validation.entity.User;

import java.util.Optional;

@Repository
public interface IUserRepopsitory extends JpaRepository<User,Long> {
    Optional<User> findByUserNameAndPassword(String username, String password);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
