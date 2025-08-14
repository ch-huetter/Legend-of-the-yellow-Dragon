package de.game.model.entity.primaryKeys;


import de.game.model.entity.Ability;
import de.game.model.entity.Effect;
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

