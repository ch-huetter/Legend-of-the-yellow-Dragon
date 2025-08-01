package de.browsergame.model.entity.primaryKeys;

import de.browsergame.model.entity.Ability;
import de.browsergame.model.entity.AbilityRestriction;
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
