package de.game.model.entity;

import de.game.model.entity.joinTable.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements UserDetails, CredentialsContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String loginName;

    @NotBlank
    private String eMail;

    @NotBlank
    @ToString.Exclude
    private String password;
    @ColumnDefault("3")
    private Integer maxCharacters;
    private Boolean active;

    private String activePlayerCharacter;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<PlayerCharacter> playerCharacters;

    @Override
    public void eraseCredentials () {
        this.password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        ArrayList<Role> roles = new ArrayList<>();
        userRoles.forEach(userRole -> {
            roles.add(userRole.getRole());
        });
        return roles;
    }

    @Override
    public String getUsername () {
        return loginName;
    }

    @Override
    public final boolean equals (Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass    = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        User user = (User) o;
        return Objects.equals(this.getLoginName(), user.getLoginName()) && Objects.equals(this.getActive(), user.getActive()) &&
               Objects.equals(this.getPassword(), user.getPassword()) && Objects.equals(this.getUserRoles().size(), user.getUserRoles().size());
    }

    @Override
    public final int hashCode () {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
