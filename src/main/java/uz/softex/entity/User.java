package uz.softex.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.softex.entity.templete.AbsEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "users")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update users set deleted=true where id=?")
@Builder(setterPrefix = "set")
public class User extends AbsEntity implements UserDetails {

    private String firstName;

    private String lastName;

    private String patronymic;

    @Column
    private String passportSeries;

    @Column(name = "password", nullable = false)
    private String password;

    @Column()
    private Date birthdate;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment photo;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired = true;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked = true;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired = true;

    @ManyToOne
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissionEnums().stream().map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
