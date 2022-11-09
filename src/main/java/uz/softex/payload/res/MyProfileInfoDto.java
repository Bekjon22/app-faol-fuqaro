package uz.softex.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


/**
 * @author Bekjon Bakhromov
 * @since 05.11.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyProfileInfoDto {

    private Long UserId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String passportInfo;
    private Date birthdate;
    private String region;
    private String district;
    private String neighborhood;
    private String destination;



}
