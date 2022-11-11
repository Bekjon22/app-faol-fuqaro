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

/**
 * @author Bekjon Bakhromov
 * @since 10.11.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "region")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update region set deleted=true where id=?")
public class Region extends AbsEntity {

    @Column(name = "name", nullable = false)
    private String name;


}
