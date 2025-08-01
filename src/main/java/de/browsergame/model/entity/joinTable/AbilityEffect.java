package de.browsergame.model.entity.joinTable;

import de.browsergame.model.entity.Ability;
import de.browsergame.model.entity.Effect;
import de.browsergame.model.entity.primaryKeys.AbilityEffectId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@IdClass(AbilityEffectId.class)
public class AbilityEffect {

    @Id
    @ManyToOne()
    @JoinColumn(name="ability_key")
    @ToString.Exclude
    private Ability ability;

    @Id
    @ManyToOne
    @JoinColumn(name="effect_id")
    @ToString.Exclude
    private Effect effect;

    @Id
    private Byte abilityTier;

    @NonNull
    private Byte duration;
    @NonNull
    private Byte targets;
    @NonNull
    private Boolean onAlly;
    @NonNull
    private Boolean onEnemy;
    @NonNull
    private Integer potency;
    @NonNull
    private Boolean isPercent;

}
