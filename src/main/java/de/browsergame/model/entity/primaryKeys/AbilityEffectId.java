package de.browsergame.model.entity.primaryKeys;


import de.browsergame.model.entity.Ability;
import de.browsergame.model.entity.Effect;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class AbilityEffectId implements Serializable {

    private Ability ability;

    private Effect effect;

    private byte abilityTier;

}

