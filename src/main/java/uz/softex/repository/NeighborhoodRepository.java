package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.softex.entity.District;
import uz.softex.entity.Neighborhood;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

    List<Neighborhood>findAllByDistrict(District district);

}
