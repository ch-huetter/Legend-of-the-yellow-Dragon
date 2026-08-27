package de.fightEngine.targetSelector;

public enum TargetType {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    QUATERNARY,
    ADDITIONAL;
    
    /**
     * Gets the targetType for a given Index.
     *
     * @param index of the loop
     * @return the TargetType for index + 1
     */
    public static TargetType indexToTargetType (int index) {
        return getTargetType(index + 1);
    }

    private static TargetType getTargetType (int value) {
        return switch (value) {
            case 1 -> PRIMARY;
            case 2 -> SECONDARY;
            case 3 -> TERTIARY;
            case 4 -> QUATERNARY;
            default -> ADDITIONAL;

        };
    }
}
