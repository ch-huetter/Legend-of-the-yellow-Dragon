package de.fightEngine.flag.implementation;

import de.fightEngine.flag.Flag;
import de.fightEngine.flag.FlagKey;
import lombok.Getter;

@Getter
public class InitiativeModifier extends Flag {

    private final int value;

    public InitiativeModifier (int duration, int value) {
        super(FlagKey.INITIATIVE_MODIFIED, duration);
        this.value = value;
    }

}
