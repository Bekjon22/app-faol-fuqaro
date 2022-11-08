package uz.softex.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import uz.softex.entity.Address;
import uz.softex.entity.Category;
import uz.softex.entity.User;
import uz.softex.payload.res.MyProfileInfoDto;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author Bekjon Bakhromov
 * @since 04.11.2022
 */
@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
@Component
public interface UserMapper {
//    @Mapping(source = "address.region",target = "region")
//    @Mapping(source = "address.district",target = "district")
//    @Mapping(source = "address.neighborhood",target = "neighborhood")
//    @Mapping(source = "address.destination",target = "destination")
//    MyProfileInfoDto toMyProfileInfoDto(@MappingTarget User user, Address address);


}
