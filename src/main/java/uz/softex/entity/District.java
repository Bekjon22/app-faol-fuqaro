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
@Entity(name = "district")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update district set deleted=true where id=?")
public class District extends AbsEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Region region;
}
