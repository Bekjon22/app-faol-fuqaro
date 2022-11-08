package uz.softex.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassportDto {
    private String birth_date;

    private String sur_name;

    private String name;

    private String patronymic_name;


}
