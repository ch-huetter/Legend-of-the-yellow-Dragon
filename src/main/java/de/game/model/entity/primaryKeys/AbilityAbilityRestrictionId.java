package de.game.model.entity.primaryKeys;

import de.game.model.entity.Ability;
import de.game.model.entity.AbilityRestriction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class AbilityAbilityRestrictionId implements Serializable {

    private AbilityRestriction restriction;

    private Ability ability;

    private Byte abilityTier;
}
