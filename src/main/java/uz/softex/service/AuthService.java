package uz.softex.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.IdentityDto;
import uz.softex.payload.req.PhoneNumberReqDto;
import uz.softex.payload.req.PhoneVerifyReqDto;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */
public interface AuthService extends UserDetailsService {
    ApiResult<?> checkPhone(PhoneNumberReqDto dto);

    ApiResult<?> verifyCode(PhoneVerifyReqDto dto);

    ApiResult<?> verifyIdentity(IdentityDto dto);
}
