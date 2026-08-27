package de.fightEngine.flag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Flag {

    private final FlagKey flagKey;
    private int duration;

    public void decreaseDuration () {
        duration--;
    }

    public boolean isDismissable () {
        return duration < 1;
    }

}
