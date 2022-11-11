package uz.softex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.IdentityDto;
import uz.softex.payload.req.PhoneNumberReqDto;
import uz.softex.payload.req.PhoneVerifyReqDto;

import javax.validation.Valid;

import static uz.softex.utils.AppConstant.BASE_PATH;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */
@RequestMapping(AuthController.AUTH_CONTROLLER)
public interface AuthController {
    String AUTH_CONTROLLER = BASE_PATH + "/auth";

    @PostMapping("/check-phone")
    ApiResult<?> checkPhone(@RequestBody @Valid PhoneNumberReqDto dto);

    @PostMapping("/verify")
    ApiResult<?> verify(@RequestBody @Valid PhoneVerifyReqDto dto);

    @PostMapping("/verify-identity")
    ApiResult<?> verifyIdentity(@RequestBody @Valid IdentityDto dto);


    @GetMapping("/test")
    ApiResult<?> test();


}
