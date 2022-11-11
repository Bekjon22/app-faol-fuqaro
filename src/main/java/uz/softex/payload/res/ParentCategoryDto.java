package uz.softex.payload.res;

import lombok.Getter;
import lombok.Setter;
import uz.softex.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bekjon Bakhromov
 * @since 04.11.2022
 */
@Getter
@Setter
public class ParentCategoryDto {
    private Long categoryId;

    private String name;
    private List<ParentCategoryDto> children;

    public ParentCategoryDto(Category category){
        this.categoryId = category.getId();
        this.name = category.getName();
        this.children = category.getChildCategories().stream().map(ParentCategoryDto::new).collect(Collectors.toList());
    }


}
