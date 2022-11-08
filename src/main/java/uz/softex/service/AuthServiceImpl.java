package uz.softex.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.softex.common.MessageService;
import uz.softex.entity.Address;
import uz.softex.entity.Role;
import uz.softex.entity.User;
import uz.softex.entity.VerificationCode;
import uz.softex.enums.RoleEnum;
import uz.softex.exception.RestException;
import uz.softex.payload.ApiResult;
import uz.softex.payload.PassportResponseDto;
import uz.softex.payload.req.IdentityDto;
import uz.softex.payload.req.PhoneNumberReqDto;
import uz.softex.payload.req.PhoneVerifyReqDto;
import uz.softex.repository.AddressRepository;
import uz.softex.repository.RoleRepository;
import uz.softex.repository.UserRepository;
import uz.softex.repository.VerificationCodeRepository;
import uz.softex.security.JwtProvider;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Bekjon Bakhromov
 * @since 31.10.2022
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${verification-code.expire-time}")
    private Long verificationExpireTime;

    @Value("${verification-code.limit}")
    private Integer limit;

    private final UserRepository userRepository;
    private final SmsService smsService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final RestTemplate restTemplate;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    @Override
    public ApiResult<?> checkPhone(PhoneNumberReqDto dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - verificationExpireTime);
        List<VerificationCode> verificationCodes = verificationCodeRepository.findAllByPhoneNumberAndCreatedAtAfterOrderByCreatedAtDesc(dto.getPhoneNumber(), timestamp);

        if (verificationCodes.size() >= limit) {
            throw RestException.restThrow(MessageService.getMessage("MESSAGE_NOT_ENOUGH_LIMIT"), HttpStatus.BAD_REQUEST);
        }

        if (!verificationCodes.isEmpty()) {
            VerificationCode lastVerificationCode = verificationCodes.get(0);
            if (!lastVerificationCode.isUsed() && lastVerificationCode.getExpireTime().after(new Timestamp(System.currentTimeMillis()))) {
                throw RestException.restThrow(MessageService.getMessage("LAST_VERIFICATION_CODE_NOT_EXPIRED"), HttpStatus.BAD_REQUEST);
            }
        }

        String verificationCode = generateCode();
        smsService.sendMessage(dto.getPhoneNumber(), verificationCode);

        verificationCodeRepository.save(new VerificationCode(dto.getPhoneNumber(), verificationCode));
        return ApiResult.successResponse(MessageService.getMessage("SMS_SENT"));
    }

    @Override
    public ApiResult<?> verifyCode(PhoneVerifyReqDto dto) {

        VerificationCode verificationCode = verificationCodeRepository.checkVerificationCode(dto.getPhoneNumber(), dto.getVerificationCode(), new Timestamp(System.currentTimeMillis()))
                .orElseThrow(() -> RestException.restThrow(MessageService.getMessage("VERIFICATION_CODE_NOT_AVAILABLE"), HttpStatus.BAD_REQUEST));

        verificationCode.setUsed(true);

        String token = jwtProvider.generateToken(dto.getPhoneNumber());
        verificationCodeRepository.save(verificationCode);
        return ApiResult.successResponse(token);


    }

    @Override
    public ApiResult<?> verifyIdentity(IdentityDto dto) {
        String passportSeries = dto.getPassportNumber().substring(0, 2);
        String passportNumber = dto.getPassportNumber().substring(2);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<PassportResponseDto> exchange;
        Optional<User> userOptional = userRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (!userOptional.isPresent()) {
            try {
                exchange = restTemplate.exchange("https://api.online-mahalla.uz/api/v1/public/tax/passport?series=" + passportSeries + "&number=" + passportNumber + "&birth_date=" + dto.getBirthdate(), HttpMethod.GET, entity, PassportResponseDto.class);

            } catch (Exception e) {
                throw RestException.restThrow(MessageService.getMessage("WRONG_PASSPORT_INFO"), HttpStatus.BAD_REQUEST);
            }

            String[] region = Objects.requireNonNull(exchange.getBody()).getData().getInfo().getData().getGiven_place().split(" ", 2);
            String[] district = Objects.requireNonNull(exchange.getBody()).getData().getInfo().getData().getBirth_place().split(" ", 2);

            Address address = new Address();
            address.setRegion(region[0]);
            address.setDistrict(district[0]);
            address.setDestination(Objects.requireNonNull(exchange.getBody()).getData().getInfo().getData().getAddress());

            Address savedAddress = addressRepository.save(address);
            Role role = roleRepository.findByType(RoleEnum.ROLE_USER).orElseThrow(() -> RestException.notFound(MessageService.getMessage("ROLE_NOT_FOUND")));

            User user = new User();
            user.setFirstName(Objects.requireNonNull(exchange.getBody()).getData().getPassport().getName());
            user.setLastName(exchange.getBody().getData().getPassport().getSur_name());
            user.setBirthdate(Date.valueOf(exchange.getBody().getData().getPassport().getBirth_date()));
            user.setPassportSeries(dto.getPassportNumber());
            user.setPatronymic(exchange.getBody().getData().getPassport().getPatronymic_name());
            user.setPassword(dto.getPassportNumber());
            user.setPhoneNumber(dto.getPhoneNumber());
            user.setAddress(savedAddress);
            user.setPassword(passwordEncoder.encode("123"));
            user.setEnabled(true);
            user.setRole(role);
            userRepository.save(user);
        }


//
        return ApiResult.successResponse("successfully verified info");


    }

    public String generateCode() {
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            verificationCode.append((int) (Math.random() * 10));
        }
        return verificationCode.toString();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username).orElseThrow(RestException::forbidden);
    }
}
