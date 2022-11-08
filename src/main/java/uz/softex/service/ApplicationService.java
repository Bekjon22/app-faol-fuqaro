package uz.softex.service;

import uz.softex.enums.Status;
import uz.softex.payload.ApiResult;
import uz.softex.payload.res.MyApplicationFullInfo;
import uz.softex.payload.res.MyApplicationInfo;
import uz.softex.payload.req.ApplicationSentDto;
import uz.softex.payload.req.ReplyApplicationDto;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
public interface ApplicationService {

    ApiResult<?> send(ApplicationSentDto dto);

    ApiResult<?> replyApplication(Long id, ReplyApplicationDto dto);

    ApiResult<List<MyApplicationInfo>> getByCategoryApp(Long id, Status status);

    ApiResult<List<MyApplicationInfo>> getAll(Long userId);

    ApiResult<MyApplicationFullInfo> getOne(Long applicationId, Long userId);
}
