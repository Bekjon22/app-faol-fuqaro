package uz.softex.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.softex.entity.templete.AbsEntity;
import uz.softex.enums.PermissionEnum;
import uz.softex.enums.RoleEnum;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update roles set deleted=true where id=?")
public class Role extends AbsEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<PermissionEnum> permissionEnums;

    @Enumerated(EnumType.STRING)
    private RoleEnum type;
}
