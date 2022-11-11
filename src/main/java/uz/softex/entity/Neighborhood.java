package uz.softex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.softex.entity.templete.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Bekjon Bakhromov
 * @since 10.11.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "neighborhood")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update neighborhood set deleted=true where id=?")
public class Neighborhood extends AbsEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private District district;

}
