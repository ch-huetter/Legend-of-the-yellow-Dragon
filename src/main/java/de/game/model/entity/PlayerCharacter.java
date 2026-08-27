package de.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.game.model.enums.PlayerClassEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "player_character")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerCharacter extends LivingEntity {

    public PlayerCharacter (Integer characterId) {
        this.id = characterId;
    }

    @NonNull
    private Integer attributePoints;
    @NonNull
    private Integer attributePointsSpend;

    @NonNull
    private Integer experienceForNextLevel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @Enumerated()
    private PlayerClassEnum playerClass;

    @Override
    public final boolean equals (Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass =
                o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass =
                this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        PlayerCharacter that = (PlayerCharacter) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode () {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
