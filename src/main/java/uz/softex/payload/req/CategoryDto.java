package uz.softex.payload.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@Getter
@Setter
public class CategoryDto {
    @NotBlank(message = "{CATEGORY_NAME_REQUIRED}")
    private String name;

    private Long parentCategoryId;

}
