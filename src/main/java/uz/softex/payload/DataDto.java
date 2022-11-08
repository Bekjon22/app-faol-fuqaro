package uz.softex.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataDto {
    private PassportDto passport;

    private InfoDto info;
}
