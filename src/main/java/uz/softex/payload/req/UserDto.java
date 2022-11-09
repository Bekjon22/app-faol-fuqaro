package uz.softex.payload.req;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * @author Bekjon Bakhromov
 * @since 02.11.2022
 */
@Getter
@Setter
public class UserDto {

    private String name;

    private String surname;

    private String patronymic;

    private String passportSeriesAndNumber;

    private Date birthdate;

    private String region;

    private String district;

    private String neighborhood;

    private String destination;

    private Long photoId;
}
