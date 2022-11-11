package uz.softex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.softex.common.MessageService;
import uz.softex.entity.Category;
import uz.softex.exception.RestException;
import uz.softex.mapper.CategoryMapper;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.CategoryDto;
import uz.softex.payload.req.CategoryReqDto;
import uz.softex.payload.res.CategoryResDto;
import uz.softex.payload.res.MainCategoryDto;
import uz.softex.payload.res.ParentCategoryDto;
import uz.softex.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public ApiResult<CategoryResDto> get(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND")));
        CategoryResDto categoryResDto = new CategoryResDto();
        categoryResDto.setId(category.getId());
        categoryResDto.setName(category.getName());
        if (category.getParentCategory() != null) {
            categoryResDto.setParentCategoryId(category.getParentCategory().getId());
        }

        return ApiResult.successResponse(categoryResDto);
    }


    @Override
    public ApiResult<?> getSub(Long parentCategoryId) {
        List<MainCategoryDto> mainCategoryDtos = new ArrayList<>();
        if (parentCategoryId == null) {
            List<Category> categoryList = categoryRepository.findAllByParentCategoryIsNull();
            for (Category category : categoryList) {
                MainCategoryDto mainCategoryDto = new MainCategoryDto();
                mainCategoryDto.setCategoryId(category.getId());
                mainCategoryDto.setCategoryName(category.getName());
                mainCategoryDtos.add(mainCategoryDto);
            }
            return ApiResult.successResponse(mainCategoryDtos);
        }

        List<Category> categoryList = categoryRepository.findAllByParentCategory_Id(parentCategoryId);
        if (categoryList.isEmpty()) {
            throw RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND"));
        }
        return ApiResult.successResponse(mapToParentCategoryDto(categoryList));
    }


    public List<ParentCategoryDto> mapToParentCategoryDto(List<Category> categories) {

        return categories.stream().map(ParentCategoryDto::new).collect(Collectors.toList());
    }


}
