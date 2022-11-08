package uz.softex.payload.req;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@Getter
@Setter
public class ApplicationSentDto {

    private Long userId;

    private String text;

    private String destination;

    private List<Long>photoIds;

    private Long categoryId;







}
