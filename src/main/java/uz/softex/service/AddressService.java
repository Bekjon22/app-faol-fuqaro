package uz.softex.service;

import uz.softex.payload.req.AddressDto;
import uz.softex.payload.ApiResult;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
public interface AddressService {
    ApiResult<?> add(AddressDto dto);
}
