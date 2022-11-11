package uz.softex;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import uz.softex.common.MessageService;
import uz.softex.entity.User;
import uz.softex.exception.RestException;
import uz.softex.payload.PassportResponseDto;
import uz.softex.payload.RegionDto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */

@RequiredArgsConstructor
public class Main {

    private static RestTemplate restTemplate;
    public static void main(String[] args) {

//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<?> entity = new HttpEntity<>(headers);
//        ResponseEntity<RegionDto> exchange;
//
//
//            try {
//
//                 exchange = restTemplate.exchange("https://fuqaro.softex.uz/api/secret/regions", HttpMethod.GET, entity, RegionDto.class);
//
//            } catch (Exception e) {
//                throw RestException.restThrow("WRONG_URL", HttpStatus.BAD_REQUEST);
//            }


    }






}
