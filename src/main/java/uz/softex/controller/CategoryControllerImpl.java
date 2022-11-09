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
    public ApiResult<?> addCategory(CategoryDto dto) {
        return categoryService.add(dto);
    }

    @Override
    public ApiResult<?> edit(CategoryReqDto categoryDto, Long id) {
        return categoryService.edit(categoryDto,id);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return categoryService.delete(id);
    }

    @Override
    public ApiResult<CategoryResDto> getOne(Long id) {
        return categoryService.get(id);
    }


    @Override
    public ApiResult<List<ParentCategoryDto>> getParent() {
        return categoryService.getParent();
    }

    @Override
    public ApiResult<List<ParentCategoryDto>> getAll() {
        return categoryService.getAll();
    }

    @Override
    public ApiResult<List<ParentCategoryDto>> getSub(Long parentCategoryId) {
        return categoryService.getSub(parentCategoryId);
    }


}
