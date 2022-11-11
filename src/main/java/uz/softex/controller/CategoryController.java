package uz.softex.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.CategoryReqDto;
import uz.softex.payload.req.CategoryDto;
import uz.softex.payload.res.CategoryResDto;
import uz.softex.payload.res.ParentCategoryDto;

import javax.validation.Valid;

import java.util.List;

import static uz.softex.utils.AppConstant.BASE_PATH;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@RequestMapping(CategoryController.CATEGORY_CONTROLLER)
@SecurityRequirement(name = "Bearer Authentication")
public interface CategoryController {
    String CATEGORY_CONTROLLER = BASE_PATH + "/category";



    @GetMapping("/{id}")
    ApiResult<CategoryResDto>getOne(@PathVariable Long id);


    @GetMapping("/view")
    ApiResult<?>getSub(@RequestParam(name = "id",required = false) Long parentCategoryId);




}
