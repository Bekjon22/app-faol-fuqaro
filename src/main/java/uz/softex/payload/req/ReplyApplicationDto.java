package uz.softex.payload.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 03.11.2022
 */
@Getter
@Setter
public class ReplyApplicationDto {

    @NotBlank(message = "{STATUS_REQUIRED}")
    private String Status;

    @NotBlank(message = "{PERFORMER_NAME_REQUIRED}")
    private String performerName;

    @NotBlank(message = "{POSITION_REQUIRED}")
    private String position;

    private List<Long> replyPhotoIds;


}
