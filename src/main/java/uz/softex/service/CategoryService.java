package uz.softex.service;

import uz.softex.payload.ApiResult;
import uz.softex.payload.req.CategoryReqDto;
import uz.softex.payload.req.CategoryDto;
import uz.softex.payload.res.CategoryResDto;
import uz.softex.payload.res.ParentCategoryDto;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
public interface CategoryService {

    ApiResult<?> add(CategoryDto dto);

    ApiResult<?> edit(CategoryReqDto categoryDto, Long id);

    ApiResult<?> delete(Long id);

    ApiResult<CategoryResDto> get(Long id);

    ApiResult<List<ParentCategoryDto>> getParent();

    ApiResult<List<ParentCategoryDto>> getAll();

    ApiResult<List<ParentCategoryDto>> getSub(Long parentCategoryId);
}
