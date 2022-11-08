package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.softex.entity.Address;
import uz.softex.entity.Category;

import java.util.Optional;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "select * from address a " +
            "INNER JOIN users u on a.id = u.address_id " +
            "where u.id =:userId",nativeQuery = true)
    Address findByUserId(Long userId);
}
