package uz.softex.payload.res;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bekjon Bakhromov
 * @since 09.11.2022
 */
@Getter
@Setter
public class CategoryResDto {

    private Long id;

    private String name;

    private Long parentCategoryId = null;

}
