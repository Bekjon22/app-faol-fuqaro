package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.softex.entity.District;
import uz.softex.entity.Region;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District>findAllByRegion(Region region);

}
