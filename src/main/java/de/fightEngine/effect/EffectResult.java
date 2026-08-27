package de.fightEngine.effect;

import de.fightEngine.result.AbstractResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class EffectResult extends AbstractResult {

    private double actionDrain;
}
