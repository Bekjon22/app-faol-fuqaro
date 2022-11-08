package uz.softex.payload.req;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;


/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@Getter
@Setter
public class IdentityDto {

    @Pattern(regexp = "[A-Z]{2}[0-9]{7}", message = "WRONG_PASSPORT_NUMBER_FORMAT")
    @NotBlank(message = "{PASSPORT_NUMBER_REQUIRED}")
    private String passportNumber;

    private Date birthdate;

    private String phoneNumber;

}
