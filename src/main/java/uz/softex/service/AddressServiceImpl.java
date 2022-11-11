package uz.softex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.softex.common.MessageService;
import uz.softex.entity.*;
import uz.softex.enums.RegionEnum;
import uz.softex.exception.RestException;
import uz.softex.payload.req.AddressDto;
import uz.softex.payload.ApiResult;
import uz.softex.payload.res.DistrictResDto;
import uz.softex.payload.res.NeighborhoodResDto;
import uz.softex.payload.res.RegionResDto;
import uz.softex.payload.res.StreetResDto;
import uz.softex.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final StreetRepository streetRepository;


    @Override
    public ApiResult<List<RegionResDto>> getRegions() {
        List<Region> all = regionRepository.findAll();
        List<RegionResDto> regionResDtos = new ArrayList<>();
        for (Region region : all) {
            RegionResDto regionResDto = new RegionResDto();
            regionResDto.setRegionId(region.getId());
            regionResDto.setName(region.getName());
            regionResDtos.add(regionResDto);
        }
        return ApiResult.successResponse(regionResDtos);
    }

    @Override
    public ApiResult<List<DistrictResDto>> getDistricts(Long regionId) {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> RestException.notFound("Region not found"));
        List<District> allByRegion = districtRepository.findAllByRegion(region);
        List<DistrictResDto> districtResDtoList = new ArrayList<>();
        for (District district : allByRegion) {
            DistrictResDto dto = new DistrictResDto();
            dto.setDistrictId(district.getId());
            dto.setName(district.getName());
            districtResDtoList.add(dto);
        }
        return ApiResult.successResponse(districtResDtoList);
    }

    @Override
    public ApiResult<List<NeighborhoodResDto>> getNeighborhood(Long districtId) {
        District district = districtRepository.findById(districtId).orElseThrow(() -> RestException.notFound("district not found"));
        List<Neighborhood> neighborhoodList = neighborhoodRepository.findAllByDistrict(district);

        List<NeighborhoodResDto> neighborhoodResDtos = new ArrayList<>();
        for (Neighborhood neighborhood : neighborhoodList) {
            NeighborhoodResDto neighborhoodResDto = new NeighborhoodResDto();
            neighborhoodResDto.setNeighborhoodId(neighborhood.getId());
            neighborhoodResDto.setName(neighborhood.getName());
            neighborhoodResDtos.add(neighborhoodResDto);
        }
        return ApiResult.successResponse(neighborhoodResDtos);
    }

    @Override
    public ApiResult<List<StreetResDto>> getStreet(Long neighborhoodId) {
        Neighborhood neighborhood = neighborhoodRepository.findById(neighborhoodId).orElseThrow(() -> RestException.notFound("Neighborhood not found"));

        List<Street> streetList = streetRepository.findAllByNeighborhood(neighborhood);
        List<StreetResDto> streetResDtos = new ArrayList<>();
        for (Street street : streetList) {
            StreetResDto streetResDto = new StreetResDto();
            streetResDto.setStreetId(street.getId());
            streetResDto.setName(street.getName());
            streetResDtos.add(streetResDto);
        }
        return ApiResult.successResponse(streetResDtos);
    }
}
