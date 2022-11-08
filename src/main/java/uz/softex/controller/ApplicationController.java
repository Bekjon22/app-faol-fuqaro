package uz.softex.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import uz.softex.enums.Status;
import uz.softex.payload.ApiResult;
import uz.softex.payload.res.MyApplicationFullInfo;
import uz.softex.payload.res.MyApplicationInfo;
import uz.softex.payload.req.ApplicationSentDto;
import uz.softex.payload.req.ReplyApplicationDto;

import javax.validation.Valid;

import java.util.List;

import static uz.softex.utils.AppConstant.BASE_PATH;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@RequestMapping(ApplicationController.APPLICATION_CONTROLLER)
public interface ApplicationController {
    String APPLICATION_CONTROLLER = BASE_PATH + "/application";

    @PostMapping("/add")
    ApiResult<?> sendApplication(@RequestBody @Valid ApplicationSentDto dto);

    @PutMapping("{id}")
    ApiResult<?> replyApplication(@PathVariable(name = "id") Long id,
                                  @RequestBody @Valid ReplyApplicationDto dto);

    @GetMapping("/get-by-category/{id}")
    ApiResult<List<MyApplicationInfo>> getByCategoryApp(@PathVariable(name = "id") Long userId, @RequestParam(name = "status") Status status);

    @GetMapping("/get-all/{id}")
    ApiResult<List<MyApplicationInfo>> getAll(@PathVariable(name = "id") Long userId);

    @GetMapping("/get/{id}")
    ApiResult<MyApplicationFullInfo> getOne(@PathVariable(name = "id") Long applicationId, @RequestParam(name = "user_id") Long userId);



}
