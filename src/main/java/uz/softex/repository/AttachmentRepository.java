package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.softex.entity.Attachment;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

}
