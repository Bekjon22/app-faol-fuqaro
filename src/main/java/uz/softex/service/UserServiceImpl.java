package uz.softex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.softex.annotation.CurrentUser;
import uz.softex.common.MessageService;
import uz.softex.entity.Address;
import uz.softex.entity.Attachment;
import uz.softex.entity.User;
import uz.softex.exception.RestException;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.UserDto;
import uz.softex.payload.res.MyProfileInfoDto;
import uz.softex.repository.AddressRepository;
import uz.softex.repository.AttachmentRepository;
import uz.softex.repository.UserRepository;

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


        Address address = addressRepository.findByUserId(id);
        if (dto.getRegion() != null) {
            address.setRegion(dto.getRegion());
        }

        if (dto.getDistrict() != null) {
            address.setDistrict(dto.getDistrict());
        }

        if (dto.getDestination() != null) {
            address.setDestination(dto.getDestination());
        }

        if (dto.getNeighborhood() != null) {
            address.setNeighborhood(dto.getNeighborhood());
        }

        user.setAddress(address);

        if (dto.getPhotoId()!=null){
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

        myProfileInfoDto.setRegion(user.getAddress().getRegion());
        myProfileInfoDto.setDistrict(user.getAddress().getDistrict());
        myProfileInfoDto.setDestination(user.getAddress().getDestination());
        myProfileInfoDto.setNeighborhood(user.getAddress().getNeighborhood());
        myProfileInfoDto.setAddressId(user.getAddress().getId());

        return ApiResult.successResponse(myProfileInfoDto);
    }




}
