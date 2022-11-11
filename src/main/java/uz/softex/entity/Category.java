package uz.softex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.softex.entity.templete.AbsEntity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "categories")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update categories set deleted=true where id=?")
public class Category extends AbsEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> childCategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;


}
