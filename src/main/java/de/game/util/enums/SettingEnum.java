package de.game.util.enums;

import lombok.Getter;

@Getter
public enum SettingEnum {

    //TODO Initializer muss noch geschrieben werden

    CHARACTER_START_ATTRIBUTE_POINTS(Integer.class, 5, "How many Attribute Points for a Character when he gets freshly Created"),
    CHARACTER_START_ABILITY_POINTS(Integer.class, 1, "How many Attribute Points for a Character when he gets freshly Created"),
    CHARACTER_START_ATTRIBUTE_HEALTH(Integer.class, 300, "Start Health for a Character on Level 1"),
    CHARACTER_START_ATTRIBUTE_STAMINA(Integer.class, 100, "Start Stamina for a Character on Level 1"),
    CHARACTER_START_ATTRIBUTE_MANA(Integer.class, 100, "Start Mana for a Character with Level 1"),
    CHARACTER_START_ATTRIBUTE_RAGE(Integer.class, 100, "Start Rage for a Character with Level1"),
    CHARACTER_START_ATTRIBUTE_ENERGY(Integer.class, 100, "Start Energy for a Character with Level1"),
    CHARACTER_START_ATTRIBUTE_ARMOR(Integer.class, 50, "Start Mana for a Character with Level1"),
    CHARACTER_START_ATTRIBUTE_RESISTANCE(Integer.class, 50, "Start Magic Resist for a Character with Level1"),
    CHARACTER_START_GOLD(Integer.class, 300, "Start Gold for a player"),
    CHARACTER_START_ATTRIBUTE_VALUE_AGILITY(Integer.class, 5, "Start value of the Agility Attribute"),
    CHARACTER_START_ATTRIBUTE_VALUE_STRENGTH(Integer.class, 5, "Start value of the Strength Attribute"),
    CHARACTER_START_ATTRIBUTE_VALUE_INTELLIGENCE(Integer.class, 5, "Start value of the Intelligence Attribute"),
    CHARACTER_START_ATTRIBUTE_VALUE_VITALITY(Integer.class, 5, "Start value of the Vitality Attribute"),
    CHARACTER_START_ATTRIBUTE_VALUE_DEXTERITY(Integer.class, 5, "Start value of the Dexterity Attribute"),

    CHARACTER_VALUE_GROWTH_PER_LEVEL_ARMOR(Integer.class, 25, "Addition Armor per Level"),
    CHARACTER_VALUE_GROWTH_PER_LEVEL_RESISTANCE(Integer.class, 15, "Additional Resistance per Level"),
    CHARACTER_VALUE_GROWTH_PER_LEVEL_HEALTH(Integer.class, 5, "Additional Health per Level"),
    CHARACTER_VALUE_GROWTH_PER_DEXTERITY_ARMOR(Integer.class, 25, "Addition Armor per dexterity"),
    CHARACTER_VALUE_GROWTH_PER_DEXTERITY_RESISTANCE(Integer.class, 5, "Additional Resistance per dexterity"),
    CHARACTER_VALUE_GROWTH_PER_VITALITY_HEALTH(Integer.class, 5, "Additional Health per Vitality");

    private final Class<?> cls;
    private final Object defaultValue;
    private String comment;

    SettingEnum (Class<?> cls, Object defaultValue) {
        this.cls = cls;

        if (defaultValue != null && !cls.isInstance(defaultValue))
            throw new IllegalArgumentException("Default Value " + this.name() + " is Type " + defaultValue.getClass() + " but expected was " + cls);

        this.defaultValue = defaultValue;
        this.comment = "";
    }

    SettingEnum (Class<?> cls, Object defaultValue, String comment) {
        this(cls, defaultValue);
        this.comment = comment;
    }
}
