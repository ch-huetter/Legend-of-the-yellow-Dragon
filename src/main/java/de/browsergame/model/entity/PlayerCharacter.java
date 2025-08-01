package de.browsergame.model.entity;

import de.browsergame.model.entity.joinTable.PlayerCharacterAttribute;
import de.browsergame.model.entity.joinTable.PlayerCharacterEquipment;
import de.browsergame.model.entity.joinTable.PlayerCharacterItem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlayerCharacter {

    @Id
    private String name;
    @NonNull
    private Integer level;
    @NonNull
    private Integer experience;
    @NonNull
    private Integer maxEnergy;
    @NonNull
    private Integer gold;
    @NonNull
    private Integer maxMana;
    @NonNull
    private Integer maxRage;
    @NonNull
    private Integer armor;
    @NonNull
    private Integer resistance;
    @NonNull
    private Integer health;
    @NonNull
    private Integer maxHealth;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "playerCharacter")
    @ToString.Exclude
    private List<PlayerCharacterItem> inventory;

    @OneToMany(mappedBy = "playerCharacter")
    @ToString.Exclude
    private List<PlayerCharacterEquipment> equipmentList;

    @OneToMany(mappedBy = "playerCharacter")
    @ToString.Exclude
    private Set<PlayerCharacterAttribute> playerAttributes;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PlayerCharacter playerCharacter = (PlayerCharacter) o;
        return getName() != null && Objects.equals(getName(), playerCharacter.getName());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
