package uz.softex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.softex.annotation.CurrentUser;
import uz.softex.entity.User;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.UserDto;
import uz.softex.payload.res.MyProfileInfoDto;
import uz.softex.service.UserService;

/**
 * @author Bekjon Bakhromov
 * @since 02.11.2022
 */
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    private final UserService userService;


    @Override
    public ApiResult<?> editProfile(Long id, UserDto dto) {
        return userService.editProfile(id,dto);
    }


    @Override
    public ApiResult<MyProfileInfoDto> getProfileInfo(User user) {
        return userService.getProfileInfo(user);
    }
}
