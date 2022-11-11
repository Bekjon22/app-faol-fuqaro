package uz.softex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.CategoryReqDto;
import uz.softex.payload.req.CategoryDto;
import uz.softex.payload.res.CategoryResDto;
import uz.softex.payload.res.ParentCategoryDto;
import uz.softex.service.CategoryService;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public ApiResult<CategoryResDto> getOne(Long id) {
        return categoryService.get(id);
    }




    @Override
    public ApiResult<?> getSub(Long parentCategoryId) {
        return categoryService.getSub(parentCategoryId);
    }


}
