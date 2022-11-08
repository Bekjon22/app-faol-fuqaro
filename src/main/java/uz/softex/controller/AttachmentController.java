package uz.softex.controller;


import io.swagger.v3.oas.annotations.Operation;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.softex.payload.ApiResult;

import java.io.IOException;

import static uz.softex.utils.AppConstant.BASE_PATH;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@RequestMapping(AttachmentController.ATTACHMENT_CONTROLLER)
public interface AttachmentController {
    String ATTACHMENT_CONTROLLER = BASE_PATH + "/attachment";

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    ApiResult<?> upload(@RequestPart(required = true) MultipartFile [] uploadfiles ) throws IOException;



}
