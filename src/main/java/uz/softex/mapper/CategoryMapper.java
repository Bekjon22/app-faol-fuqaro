package uz.softex.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import uz.softex.entity.Category;
import uz.softex.payload.res.ParentCategoryDto;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author Bekjon Bakhromov
 * @since 04.11.2022
 */
@Component
@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
public interface CategoryMapper {

//    List<ParentCategoryDto> toParentCategoryDto(List<Category> categories);

}
