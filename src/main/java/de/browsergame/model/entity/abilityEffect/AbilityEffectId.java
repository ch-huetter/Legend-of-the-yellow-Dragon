package de.browsergame.model.entity.abilityEffect;


import de.browsergame.model.entity.Ability;
import de.browsergame.model.entity.Effect;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode

public class AbilityEffectId implements Serializable {

    private Ability ability;

    private Effect effect;

    private byte abilityTier;

}

