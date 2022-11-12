package uz.softex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.softex.annotation.CurrentUser;
import uz.softex.common.MessageService;
import uz.softex.entity.*;
import uz.softex.exception.RestException;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.UserDto;
import uz.softex.payload.res.MyProfileInfoDto;
import uz.softex.repository.*;

/**
 * @author Bekjon Bakhromov
 * @since 02.11.2022
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AttachmentRepository attachmentRepository;
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final StreetRepository streetRepository;

    @Override
    public ApiResult<?> editProfile(Long id, UserDto dto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                RestException.notFound(MessageService.getMessage("USER_NOT_FOUND")));

        if (dto.getName() != null) {
            user.setFirstName(dto.getName());
        }

        if (dto.getSurname() != null) {
            user.setLastName(dto.getSurname());
        }

        if (dto.getPatronymic() != null) {
            user.setPatronymic(dto.getPatronymic());
        }
//
        if (dto.getPassportSeriesAndNumber() != null) {
            user.setPassportSeries(dto.getPassportSeriesAndNumber());
        }
        if (dto.getBirthdate() != null) {
            user.setBirthdate(dto.getBirthdate());
        }

        if (dto.getRegionId() != null) {
            Region region = regionRepository.findById(dto.getRegionId()).orElseThrow(() -> RestException.notFound("Region not found"));
            user.setRegion(region);
        }

        if (dto.getDistrictId() != null) {
            District district = districtRepository.findById(dto.getDistrictId()).orElseThrow(() -> RestException.notFound("District not found"));
            user.setDistrict(district);
        }

        if (dto.getNeighborhoodId() != null) {
            Neighborhood neighborhood = neighborhoodRepository.findById(dto.getNeighborhoodId()).orElseThrow(() -> RestException.notFound("Neighborhood not found"));
            user.setNeighborhood(neighborhood);
        }

        if (dto.getStreetId() != null) {
            Street street = streetRepository.findById(dto.getStreetId()).orElseThrow(() -> RestException.notFound("Neighborhood not found"));
            user.setStreet(street);
        }


//        user.setAddress(address);

        if (dto.getPhotoId() != null) {
            Attachment attachment = attachmentRepository.findById(dto.getPhotoId()).orElseThrow(() -> RestException.notFound("Photo not found!"));
            user.setPhoto(attachment);
        }


        userRepository.save(user);

        return ApiResult.successResponse("Successfully edited");
    }

    @Override
    public ApiResult<MyProfileInfoDto> getProfileInfo(User user) {
//        User user = userRepository.findById(user).orElseThrow(() ->
//                RestException.notFound(MessageService.getMessage("USER_NOT_FOUND")));


        MyProfileInfoDto myProfileInfoDto = new MyProfileInfoDto();
        myProfileInfoDto.setUserId(user.getId());
        myProfileInfoDto.setFirstName(user.getFirstName());
        myProfileInfoDto.setLastName(user.getLastName());
        myProfileInfoDto.setPatronymic(user.getPatronymic());

        myProfileInfoDto.setPassportInfo(user.getPassportSeries());
        myProfileInfoDto.setBirthdate(user.getBirthdate());

        myProfileInfoDto.setRegion(user.getRegion().getName());
        myProfileInfoDto.setDistrict(user.getDistrict().getName());

        if (user.getNeighborhood()!=null){
            myProfileInfoDto.setNeighborhood(user.getNeighborhood().getName());
        }

        if (user.getStreet()!=null){
            myProfileInfoDto.setStreet(user.getStreet().getName());
        }

        return ApiResult.successResponse(myProfileInfoDto);
    }


}
