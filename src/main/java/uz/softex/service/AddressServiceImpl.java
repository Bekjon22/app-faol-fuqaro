package uz.softex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.softex.common.MessageService;
import uz.softex.entity.Address;
import uz.softex.enums.RegionEnum;
import uz.softex.payload.req.AddressDto;
import uz.softex.payload.ApiResult;
import uz.softex.repository.AddressRepository;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public ApiResult<?> add(AddressDto dto) {


        return ApiResult.successResponse(MessageService.getMessage("ADDRESS_SUCCESSFULLY_ADDED"));
    }
}
