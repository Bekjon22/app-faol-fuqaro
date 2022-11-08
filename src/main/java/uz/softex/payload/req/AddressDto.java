package uz.softex.payload.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */
@Getter
@Setter
public class AddressDto {

    private String region;

    private String district;

    private String neighborhood;

    private String destination;

    private Double lat;

    private Double lon;

}
