package de.game.util;

public class PercentCalculator {

    public static Integer calculatePercent (Integer value1, Integer value2) {
        if (value1 == null || value1 == 0 || value2 == null || value2 == 0)
            return 0;
        return value1 / value2 * 100;
    }
}
