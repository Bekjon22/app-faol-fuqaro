package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.softex.entity.Address;
import uz.softex.entity.Region;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
public interface RegionRepository extends JpaRepository<Region, Long> {


}
