package de.browsergame.model.entity.abilityAbilityRestriction;

import de.browsergame.model.entity.Ability;
import de.browsergame.model.entity.AbilityRestriction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class AbilityAbilityRestrictionId implements Serializable {

    private AbilityRestriction restriction;

    private Ability ability;

    private Byte abilityTier;
}
