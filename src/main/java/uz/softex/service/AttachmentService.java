package uz.softex.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.softex.payload.ApiResult;

import java.io.IOException;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
public interface AttachmentService {
    ApiResult<?> upload(MultipartFile[] uploadfiles);

//    ApiResult<?> upload(MultipartHttpServletRequest request) throws IOException;
}
