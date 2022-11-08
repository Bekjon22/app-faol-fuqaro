package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.softex.entity.Role;
import uz.softex.enums.RoleEnum;

import java.util.Optional;

/**
 * @author Bekjon Bakhromov
 * @since 04.11.2022
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByType(RoleEnum type);
}
