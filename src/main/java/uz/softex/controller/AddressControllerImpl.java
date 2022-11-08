package uz.softex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.softex.payload.req.AddressDto;
import uz.softex.payload.ApiResult;
import uz.softex.service.AddressService;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController{

        private final AddressService addressService;

    @Override
    public ApiResult<?> add(AddressDto dto) {
        return addressService.add(dto);
    }



}
