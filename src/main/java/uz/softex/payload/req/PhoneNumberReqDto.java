package uz.softex.payload.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */
@Getter
@Setter
public class PhoneNumberReqDto {

    @Pattern(regexp = "\\+[9]{2}[8][0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    @NotBlank(message = "{PHONE_NUMBER_REQUIRED}")
    private String phoneNumber;
}
