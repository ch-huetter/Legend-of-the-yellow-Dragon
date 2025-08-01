package de.browsergame.model.entity.joinTable;

import de.browsergame.model.entity.Ability;
import de.browsergame.model.entity.AbilityRestriction;
import de.browsergame.model.entity.primaryKeys.AbilityAbilityRestrictionId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@IdClass(AbilityAbilityRestrictionId.class)
public class AbilityAbilityRestriction {
    @Id
    @ManyToOne()
    @JoinColumn(name="restriction_key")
    private AbilityRestriction restriction;

    @Id
    @ManyToOne()
    @JoinColumn(name="ability_key")
    private Ability ability;

    @Id
    @NonNull
    private Byte abilityTier;

    @NonNull
    private Integer value;


}
