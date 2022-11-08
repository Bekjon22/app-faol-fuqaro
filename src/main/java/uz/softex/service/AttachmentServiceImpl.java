package uz.softex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.softex.entity.Attachment;
import uz.softex.entity.AttachmentContent;
import uz.softex.payload.ApiResult;
import uz.softex.repository.AttachmentContentRepository;
import uz.softex.repository.AttachmentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository contentRepository;


//    @Override
//    public ApiResult<?> upload(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//        List<Long> photoids = new ArrayList<>();
//
//        while (fileNames.hasNext()) {
//            MultipartFile file = request.getFile(fileNames.next());
//            assert file != null;
//            Attachment attachment = Attachment.builder()
//                    .name(file.getOriginalFilename())
//                    .size(file.getSize())
//                    .contentType(file.getContentType()).build();
//            Attachment save = attachmentRepository.save(attachment);
//
//            AttachmentContent content = AttachmentContent.builder()
//                    .attachment(save)
//                    .bytes(file.getBytes()).build();
//            AttachmentContent savedPhoto = contentRepository.save(content);
//            photoids.add(savedPhoto.getId());
//
//        }
//
//        return ApiResult.successResponse(photoids);
//    }

    @Override
    public ApiResult<?> upload(MultipartFile[] uploadfiles) {
        List<Long> photoids = new ArrayList<>();

        for (MultipartFile multipartFile : uploadfiles) {
            Attachment attachment = Attachment.builder()
                    .name(multipartFile.getOriginalFilename())
                    .size(multipartFile.getSize())
                    .contentType(multipartFile.getContentType()).build();
            Attachment save = attachmentRepository.save(attachment);

            AttachmentContent content = null;
            try {
                content = AttachmentContent.builder()
                        .attachment(save)
                        .bytes(multipartFile.getBytes()).build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AttachmentContent savedPhoto = contentRepository.save(content);
            photoids.add(savedPhoto.getId());

        }
        return ApiResult.successResponse(photoids);
    }
}
