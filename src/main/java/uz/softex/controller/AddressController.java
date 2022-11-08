package uz.softex.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.softex.payload.req.AddressDto;
import uz.softex.payload.ApiResult;

import javax.validation.Valid;

import static uz.softex.utils.AppConstant.BASE_PATH;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@RequestMapping(AddressController.ADDRESS_CONTROLLER)
public interface AddressController {
    String ADDRESS_CONTROLLER = BASE_PATH + "/address";

    @PostMapping ("/add")
    ApiResult<?> add(@RequestBody @Valid AddressDto dto);


}
