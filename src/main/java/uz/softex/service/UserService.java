package uz.softex.service;

import uz.softex.entity.User;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.UserDto;
import uz.softex.payload.res.MyProfileInfoDto;

/**
 * @author Bekjon Bakhromov
 * @since 02.11.2022
 */
public interface UserService {

    ApiResult<?> editProfile(Long id,UserDto dto);

    ApiResult<MyProfileInfoDto> getProfileInfo(User user);
}
