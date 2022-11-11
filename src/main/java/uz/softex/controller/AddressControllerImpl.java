package uz.softex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.softex.payload.req.AddressDto;
import uz.softex.payload.ApiResult;
import uz.softex.payload.res.DistrictResDto;
import uz.softex.payload.res.NeighborhoodResDto;
import uz.softex.payload.res.RegionResDto;
import uz.softex.payload.res.StreetResDto;
import uz.softex.service.AddressService;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController{

        private final AddressService addressService;


        @Override
        public ApiResult<List<RegionResDto>> getRegions() {
                return addressService.getRegions();
        }

        @Override
        public ApiResult<List<DistrictResDto>> getDistricts(Long regionId) {
                return addressService.getDistricts(regionId);
        }

        @Override
        public ApiResult<List<NeighborhoodResDto>> getNeighborhood(Long districtId) {
                return addressService.getNeighborhood(districtId);
        }

        @Override
        public ApiResult<List<StreetResDto>> getStreet(Long neighborhoodId) {
                return addressService.getStreet(neighborhoodId);
        }
}
