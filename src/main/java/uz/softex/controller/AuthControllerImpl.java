package uz.softex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.IdentityDto;
import uz.softex.payload.req.PhoneNumberReqDto;
import uz.softex.payload.req.PhoneVerifyReqDto;
import uz.softex.service.AuthService;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */
@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public ApiResult<?> checkPhone(PhoneNumberReqDto dto) {
        return authService.checkPhone(dto) ;
    }

    @Override
    public ApiResult<?> verify(PhoneVerifyReqDto dto) {
        return authService.verifyCode(dto);
    }

    @Override
    public ApiResult<?> verifyIdentity(IdentityDto dto) {
        return authService.verifyIdentity(dto);
    }
}
