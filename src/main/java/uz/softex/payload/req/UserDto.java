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

    private Long regionId;

    private Long districtId;

    private Long neighborhoodId;

    private Long streetId;

    private Long photoId;
}
