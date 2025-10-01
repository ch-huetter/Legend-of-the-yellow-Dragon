package de.game.model.entity;

import de.game.model.entity.joinTable.PlayerCharacterAttribute;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "player_character")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlayerCharacter extends AbstractCharacter {

    @NonNull
    private String name;
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
    @NonNull
    private Integer abilityPoints;
    @NonNull
    private Integer abilityPointsSpend;
    @NonNull
    private Integer abilityTreePoints;
    @NonNull
    private Integer abilityTreePointsSpend;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "characterName")
    @ToString.Exclude
    @MapKey(name = "attributeKey")
    private Map<String, PlayerCharacterAttribute> attributes;

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
        return getName() != null && Objects.equals(getName(), playerCharacter.getName());
    }

    @Override
    public final int hashCode () {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
