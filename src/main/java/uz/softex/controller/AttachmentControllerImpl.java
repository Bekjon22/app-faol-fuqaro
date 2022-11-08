package uz.softex.controller;




import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.softex.payload.ApiResult;
import uz.softex.service.AttachmentService;

import java.io.IOException;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController{

    private final AttachmentService service;


//    @Override
//    public ApiResult<?> upload(MultipartHttpServletRequest request) throws IOException {
//        return service.upload(request);
//    }


    @Override
    public ApiResult<?> upload(MultipartFile[] uploadfiles) throws IOException {
        return service.upload(uploadfiles);
    }
}
