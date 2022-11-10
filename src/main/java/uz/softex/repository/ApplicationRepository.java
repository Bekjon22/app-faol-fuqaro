package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.softex.entity.Application;
import uz.softex.enums.Status;
import uz.softex.payload.MyApplicationInfoProjection;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "select c.id as application_id,c.name as category_name, a.status as status_name " +
            "from application a " +
            "INNER JOIN categories c on c.id = a.category.id " +
            "where a.user.id =:userId and a.status =:status")
    List<MyApplicationInfoProjection> getApplicationByStatus(Long userId, Status status);


    @Query(value = "select c.id as application_id, c.name as category_name, a.status as status_name " +
            "from application a " +
            "INNER JOIN categories c on c.id = a.category.id " +
            "where a.user.id =:userId")
    List<MyApplicationInfoProjection> getAllApplication(Long userId);

    Application findByIdAndUserId(Long id, Long user_id);
}
