package uz.softex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.softex.entity.templete.AbsEntity;
import uz.softex.enums.Status;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "application")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update application set deleted=true where id=?")
public class Application extends AbsEntity {

    @Column(nullable = false,length = 1000)
    private String text;

    @OneToMany
    private List<Attachment> photoIds;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private String destination;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String performerName;

    private String position;

    private Timestamp resultDate;

    @OneToMany
    private List<Attachment> replyPhotoIds;


}
