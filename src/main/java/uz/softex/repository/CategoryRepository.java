package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.softex.entity.Category;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category>findAllByParentCategoryIsNull();

    List<Category>findAllByParentCategory_Id(Long parentCategory_id);

}
