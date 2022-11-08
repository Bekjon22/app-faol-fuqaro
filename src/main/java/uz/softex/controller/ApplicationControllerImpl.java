package uz.softex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.softex.enums.Status;
import uz.softex.payload.ApiResult;
import uz.softex.payload.res.MyApplicationFullInfo;
import uz.softex.payload.res.MyApplicationInfo;
import uz.softex.payload.req.ApplicationSentDto;
import uz.softex.payload.req.ReplyApplicationDto;
import uz.softex.service.ApplicationService;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@RestController
@RequiredArgsConstructor
public class ApplicationControllerImpl implements ApplicationController{

    private final ApplicationService applicationService;

    @Override
    public ApiResult<?> sendApplication(ApplicationSentDto dto) {
        return applicationService.send(dto);
    }

    @Override
    public ApiResult<?> replyApplication(Long id, ReplyApplicationDto dto) {
        return applicationService.replyApplication(id,dto);
    }

    @Override
    public ApiResult<List<MyApplicationInfo>> getByCategoryApp(Long id, Status status) {
        return applicationService.getByCategoryApp(id,status);
    }

    @Override
    public ApiResult<List<MyApplicationInfo>> getAll(Long userId) {
        return applicationService.getAll(userId);
    }

    @Override
    public ApiResult<MyApplicationFullInfo> getOne(Long applicationId, Long userId) {
        return applicationService.getOne(applicationId,userId);
    }
}
