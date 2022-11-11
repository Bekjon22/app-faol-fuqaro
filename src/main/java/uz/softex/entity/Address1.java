package uz.softex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.softex.entity.templete.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Bekjon Bakhromov
 * @since 11.11.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "address1")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update address1 set deleted=true where id=?")
public class Address1 extends AbsEntity {

    @ManyToOne
    private Street street;

}
