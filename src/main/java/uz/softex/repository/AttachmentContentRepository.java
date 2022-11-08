package uz.softex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.softex.entity.AttachmentContent;

/**
 * @author Bekjon Bakhromov
 * @since 01.11.2022
 */
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Long> {

}
