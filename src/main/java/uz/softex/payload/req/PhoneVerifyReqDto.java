package uz.softex.payload.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@Getter
@Setter
public class PhoneVerifyReqDto {
    @NotBlank(message = "{VERIFICATION_CODE_REQUIRED}")
    private String verificationCode;

    @Pattern(regexp = "\\+[9]{2}[8][0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    @NotBlank(message = "{PHONE_NUMBER_REQUIRED}")
    private String phoneNumber;
}
