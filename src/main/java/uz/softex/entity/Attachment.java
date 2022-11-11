package uz.softex.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.softex.entity.templete.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "attachments")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update attachments set deleted=true where id=?")
public class Attachment extends AbsEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "size", nullable = false)
    private Long size;

    @ManyToOne
    private Application application;
}
