package uz.softex.payload.req;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@Getter
@Setter
public class CategoryReqDto {

    private String name;

    private Long parentCategoryId;

}
