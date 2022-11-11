package uz.softex.service;

import uz.softex.payload.req.AddressDto;
import uz.softex.payload.ApiResult;
import uz.softex.payload.res.DistrictResDto;
import uz.softex.payload.res.NeighborhoodResDto;
import uz.softex.payload.res.RegionResDto;
import uz.softex.payload.res.StreetResDto;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
public interface AddressService {

    ApiResult<List<RegionResDto>> getRegions();

    ApiResult<List<DistrictResDto>> getDistricts(Long regionId);

    ApiResult<List<NeighborhoodResDto>> getNeighborhood(Long districtId);

    ApiResult<List<StreetResDto>> getStreet(Long neighborhoodId);
}
