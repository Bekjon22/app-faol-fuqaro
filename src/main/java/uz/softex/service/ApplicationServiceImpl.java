package uz.softex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.softex.common.MessageService;
import uz.softex.entity.Application;
import uz.softex.entity.Attachment;
import uz.softex.entity.Category;
import uz.softex.entity.User;
import uz.softex.enums.Status;
import uz.softex.exception.RestException;
import uz.softex.payload.ApiResult;
import uz.softex.payload.res.MyApplicationFullInfo;
import uz.softex.payload.res.MyApplicationInfo;
import uz.softex.payload.MyApplicationInfoProjection;
import uz.softex.payload.req.ApplicationSentDto;
import uz.softex.payload.req.ReplyApplicationDto;
import uz.softex.repository.ApplicationRepository;
import uz.softex.repository.AttachmentRepository;
import uz.softex.repository.CategoryRepository;
import uz.softex.repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ApiResult<?> send(ApplicationSentDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND")));


        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> RestException.notFound(MessageService.getMessage("USER_NOT_FOUND")));

        List<Attachment> attachmentList = attachmentRepository.findAll();
        Application application = new Application();
        List<Attachment> attachments = new ArrayList<>();
        for (Attachment attachment : attachmentList) {
            for (Long photoId : dto.getPhotoIds()) {
                if (Objects.equals(attachment.getId(), photoId)) {
                    attachments.add(attachment);
                }
            }
        }
        application.setPhotoIds(attachments);
        application.setText(dto.getText());
        application.setDestination(dto.getDestination());
        application.setStatus(Status.OPEN);
        application.setUser(user);
        application.setCategory(category);
        applicationRepository.save(application);
        return ApiResult.successResponse(MessageService.getMessage("APPLICATION_SUCCESSFULLY_SEND"));

    }

    @Override
    public ApiResult<?> replyApplication(Long id, ReplyApplicationDto dto) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> RestException.notFound(MessageService.getMessage("APPLICATION_NOT_FOUND")));
        List<Attachment> attachmentList = attachmentRepository.findAll();
        List<Attachment> attachments = new ArrayList<>();
        for (Attachment attachment : attachmentList) {
            for (Long photoId : dto.getReplyPhotoIds()) {
                if (Objects.equals(attachment.getId(), photoId)) {
                    attachments.add(attachment);
                }
            }
        }
        application.setReplyPhotoIds(attachments);
        application.setStatus(Status.valueOf(dto.getStatus()));
        application.setPerformerName(dto.getPerformerName());
        application.setPosition(dto.getPosition());
        application.setResultDate(new Timestamp(System.currentTimeMillis()));
        applicationRepository.save(application);

        return ApiResult.successResponse(MessageService.getMessage("SUCCESSFULLY_REPLIED"));
    }

    @Override
    public ApiResult<List<MyApplicationInfo>> getByStatusApp(Long id,Status status) {

        List<MyApplicationInfoProjection> myAppByStatus = applicationRepository.getApplicationByStatus(id,status);

        if (myAppByStatus.isEmpty()) {
            throw RestException.restThrow(MessageService.getMessage("APPLICATION_NOT_FOUND"), HttpStatus.BAD_REQUEST);
        }
        List<MyApplicationInfo> myApplicationInfoList = new ArrayList<>();

        MyApplicationInfo myApplicationInfo = new MyApplicationInfo();

        for (MyApplicationInfoProjection myApplicationInfoProjection : myAppByStatus) {
            myApplicationInfo.setApplicationId(myApplicationInfoProjection.getApplication_id());
            myApplicationInfo.setCategoryName(myApplicationInfoProjection.getCategory_name());
            myApplicationInfo.setStatusName(myApplicationInfoProjection.getStatus_name());
            myApplicationInfoList.add(myApplicationInfo);
        }

        return ApiResult.successResponse(myApplicationInfoList);
    }

    @Override
    public ApiResult<List<MyApplicationInfo>> getAll(Long userId) {
        List<MyApplicationInfoProjection> allApplication = applicationRepository.getAllApplication(userId);

        if (allApplication.isEmpty()) {
            throw RestException.restThrow(MessageService.getMessage("APPLICATION_NOT_FOUND"), HttpStatus.BAD_REQUEST);
        }
        List<MyApplicationInfo> myApplicationInfoList = new ArrayList<>();

        MyApplicationInfo myApplicationInfo = new MyApplicationInfo();

        for (MyApplicationInfoProjection myApplicationInfoProjection : allApplication) {
            myApplicationInfo.setApplicationId(myApplicationInfoProjection.getApplication_id());
            myApplicationInfo.setCategoryName(myApplicationInfoProjection.getCategory_name());
            myApplicationInfo.setStatusName(myApplicationInfoProjection.getStatus_name());
            myApplicationInfoList.add(myApplicationInfo);
        }
        return ApiResult.successResponse(myApplicationInfoList);
    }

    @Override
    public ApiResult<MyApplicationFullInfo> getOne(Long applicationId, Long userId) {
        Application application = applicationRepository.findByIdAndUserId(applicationId, userId);
        if (application==null){
            throw RestException.restThrow(MessageService.getMessage("APPLICATION_NOT_FOUND"),HttpStatus.BAD_REQUEST);
        }

        MyApplicationFullInfo myApplicationFullInfo = new MyApplicationFullInfo();
        myApplicationFullInfo.setText(application.getText());
        myApplicationFullInfo.setCategoryName(application.getCategory().getName());
        myApplicationFullInfo.setDestination(application.getDestination());
        myApplicationFullInfo.setStatus(application.getStatus());
        myApplicationFullInfo.setSubmittedDate(application.getCreatedAt());
        if (application.getStatus()!=Status.OPEN){
            myApplicationFullInfo.setPosition(application.getPosition());
            myApplicationFullInfo.setPerformerName(application.getPerformerName());
            myApplicationFullInfo.setResultDate(application.getResultDate());
        }

        return ApiResult.successResponse(myApplicationFullInfo);
    }


}
