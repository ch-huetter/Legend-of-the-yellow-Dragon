package de.game.model.entity.primaryKeys;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class AbilityEffectId implements Serializable {

    private String ability;

    private Integer effect;

    private byte abilityTier;

}

