package uz.softex.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bekjon Bakhromov
 * @since 11.11.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionDto {

    private Long neighborhood_id;

    private Long street_id;

    private String name_uz;

}
