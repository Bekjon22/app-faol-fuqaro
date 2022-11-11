package uz.softex.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.softex.entity.Category;

import java.util.stream.Collectors;

/**
 * @author Bekjon Bakhromov
 * @since 11.11.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainCategoryDto {

    private Long categoryId;

    private String categoryName;

}
