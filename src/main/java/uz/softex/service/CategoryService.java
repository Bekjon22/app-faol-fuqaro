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

    ApiResult<CategoryResDto> get(Long id);


    ApiResult<?> getSub(Long parentCategoryId);
}
