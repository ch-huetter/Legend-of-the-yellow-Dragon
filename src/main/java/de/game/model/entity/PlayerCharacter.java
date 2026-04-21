package de.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.game.model.entity.joinTable.PlayerCharacterAttribute;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "player_character")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class PlayerCharacter extends LivingEntity {

    @NonNull
    private Integer energy;
    @NonNull
    private Integer maxEnergy;
    @NonNull
    private Integer health;

    @NonNull
    private Integer attributePoints;
    @NonNull
    private Integer attributePointsSpend;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "characterName", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "attributeKey")
    private List<PlayerCharacterAttribute> attributes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_class_key")
    private PlayerClass playerClass;

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
        PlayerCharacter playerCharacter = (PlayerCharacter) o;
        return Objects.equals(getName(), playerCharacter.getName());
    }

    @Override
    public final int hashCode () {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
