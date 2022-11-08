package uz.softex.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.*;
import uz.softex.annotation.CurrentUser;
import uz.softex.entity.User;
import uz.softex.payload.ApiResult;
import uz.softex.payload.req.UserDto;
import uz.softex.payload.res.MyProfileInfoDto;

import javax.validation.Valid;

import static uz.softex.utils.AppConstant.BASE_PATH;

/**
 * @author Bekjon Bakhromov
 * @since 02.11.2022
 */
@RequestMapping(UserController.USER_CONTROLLER)
public interface UserController {
    String USER_CONTROLLER = BASE_PATH + "/user";

    @PatchMapping("/edit/{id}")
    ApiResult<?> editProfile(@PathVariable Long id, @RequestBody @Valid UserDto dto);

    @GetMapping("/get-profile-info")
    ApiResult<MyProfileInfoDto> getProfileInfo(@CurrentUser User user);


}
