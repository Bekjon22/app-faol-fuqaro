package uz.softex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.softex.entity.templete.AbsEntity;
import uz.softex.enums.RegionEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "address")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update address set deleted=true where id=?")
public class Address extends AbsEntity {

    private String region;

    private String district;

    private String neighborhood;

    private String destination;

    private Double lat;

    private Double lon;


}
