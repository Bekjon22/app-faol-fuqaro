package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.softex.entity.User;

import java.util.Optional;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}
