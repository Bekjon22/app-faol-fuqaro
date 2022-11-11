package uz.softex.controller;

import org.springframework.web.bind.annotation.*;
import uz.softex.payload.RegionDto;
import uz.softex.payload.req.AddressDto;
import uz.softex.payload.ApiResult;
import uz.softex.payload.res.DistrictResDto;
import uz.softex.payload.res.NeighborhoodResDto;
import uz.softex.payload.res.RegionResDto;
import uz.softex.payload.res.StreetResDto;

import javax.validation.Valid;

import java.util.List;

import static uz.softex.utils.AppConstant.BASE_PATH;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@RequestMapping(AddressController.ADDRESS_CONTROLLER)
public interface AddressController {
    String ADDRESS_CONTROLLER = BASE_PATH + "/address";

    @GetMapping("/region")
    ApiResult<List<RegionResDto>> getRegions();

    @GetMapping("/district")
    ApiResult<List<DistrictResDto>> getDistricts(@RequestParam(name = "regionId") Long regionId);

    @GetMapping("/neighborhood")
    ApiResult<List<NeighborhoodResDto>> getNeighborhood(@RequestParam(name = "districtId") Long districtId);

      @GetMapping("/street")
    ApiResult<List<StreetResDto>> getStreet(@RequestParam(name = "neighborhoodId") Long neighborhoodId);



}
