package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.softex.entity.VerificationCode;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {

    List<VerificationCode> findAllByPhoneNumberAndCreatedAtAfterOrderByCreatedAtDesc(String phoneNumber, Timestamp createdAt);


    @Query("select v from VerificationCode v where v.phoneNumber = ?1 and v.verificationCode = ?2 and v.expireTime > ?3 and v.used=false")
    Optional<VerificationCode> checkVerificationCode(String phoneNumber, String verificationCode, Timestamp expireTime);

}
