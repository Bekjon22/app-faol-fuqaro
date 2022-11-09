package uz.softex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.softex.common.MessageService;
import uz.softex.entity.Category;
import uz.softex.exception.RestException;
import uz.softex.mapper.CategoryMapper;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.CategoryReqDto;
import uz.softex.payload.req.CategoryDto;
import uz.softex.payload.res.CategoryResDto;
import uz.softex.payload.res.ParentCategoryDto;
import uz.softex.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ApiResult<?> add(CategoryDto dto) {

        Category parentCategory = null;
        if (dto.getParentCategoryId() != null) {
            parentCategory = categoryRepository.findById(dto.getParentCategoryId()).orElseThrow(() -> RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND")));
        }
        Category category = new Category();
        category.setName(dto.getName());
        category.setParentCategory(parentCategory);
        categoryRepository.save(category);
        return ApiResult.successResponse(MessageService.getMessage("CATEGORY_SUCCESSFULLY_ADDED"));

    }

    @Override
    public ApiResult<?> edit(CategoryReqDto categoryDto, Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND")));

        Category parentCategory = null;
        if (categoryDto.getParentCategoryId() != null) {
            parentCategory = categoryRepository.findById(categoryDto.getParentCategoryId()).orElseThrow(() -> RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND")));
        }

        category.setName(categoryDto.getName());
        category.setParentCategory(parentCategory);
        categoryRepository.save(category);
        return ApiResult.successResponse(MessageService.getMessage("CATEGORY_SUCCESSFULLY_EDITED"));

    }

    @Override
    public ApiResult<CategoryResDto> get(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND")));
        CategoryResDto categoryResDto = new CategoryResDto();
        categoryResDto.setId(category.getId());
        categoryResDto.setName(category.getName());
        if (category.getParentCategory()!=null){
            categoryResDto.setParentCategoryId(category.getParentCategory().getId());
        }

        return ApiResult.successResponse(categoryResDto);
    }

    @Override
    public ApiResult<List<ParentCategoryDto>> getParent() {
        List<Category> categoryList = categoryRepository.findAllByParentCategoryIsNull();
        mapToParentCategoryDto(categoryList);
        return ApiResult.successResponse(mapToParentCategoryDto(categoryList));
    }

    @Override
    public ApiResult<List<ParentCategoryDto>> getAll() {
        List<Category> all = categoryRepository.findAll();
        return ApiResult.successResponse(mapToParentCategoryDto(all));
    }

    @Override
    public ApiResult<List<ParentCategoryDto>> getSub(Long parentCategoryId) {

        List<Category> categoryList = categoryRepository.findAllByParentCategory_Id(parentCategoryId);
        if (categoryList.isEmpty()) {
            throw RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND"));
        }
        return ApiResult.successResponse(mapToParentCategoryDto(categoryList));
    }

    @Override
    public ApiResult<?> delete(Long id) {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(() -> RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND"))));
        return ApiResult.successResponse(MessageService.getMessage("CATEGORY_SUCCESSFULLY_DELETED"));
    }


    public List<ParentCategoryDto> mapToParentCategoryDto(List<Category> categories) {
        List<ParentCategoryDto> parentCategoryDtos = new ArrayList<>();
        for (Category category : categories) {
            ParentCategoryDto parentCategoryDto = new ParentCategoryDto();
            parentCategoryDto.setCategoryId(category.getId());
            parentCategoryDto.setName(category.getName());
            parentCategoryDtos.add(parentCategoryDto);
        }
        return parentCategoryDtos;
    }


}
