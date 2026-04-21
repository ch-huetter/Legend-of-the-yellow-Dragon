package de.game.model.enums;

import lombok.Getter;

/**
 * All Settings with its Default Values
 */
@Getter
public enum SettingEnum {
    CHARACTER_START_LEVEL("1", "Start Level for Charakters"),
    CHARACTER_START_ATTRIBUTE_POINTS("5", "How many Attribute Points for a Character when he gets freshly Created"),
    CHARACTER_START_ATTRIBUTE_HEALTH("300", "Start Health for a Character on Level 1"),
    CHARACTER_START_ATTRIBUTE_STAMINA("100", "Start Stamina for a Character on Level 1"),
    CHARACTER_START_ATTRIBUTE_MANA("100", "Start Mana for a Character with Level 1"),
    CHARACTER_START_ATTRIBUTE_ENERGY("100", "Start Energy for a Character with Level1"),
    CHARACTER_START_ATTRIBUTE_ARMOR("50", "Start Mana for a Character with Level1"),
    CHARACTER_START_ATTRIBUTE_RESISTANCE("50", "Start Magic Resist for a Character with Level1"),
    CHARACTER_START_GOLD("300", "Start Gold for a player"),
    CHARACTER_START_ATTRIBUTE_VALUE_AGILITY("5", "Start value of the Agility Attribute"),
    CHARACTER_START_ATTRIBUTE_VALUE_STRENGTH("5", "Start value of the Strength Attribute"),
    CHARACTER_START_ATTRIBUTE_VALUE_INTELLIGENCE("5", "Start value of the Intelligence Attribute"),
    CHARACTER_START_ATTRIBUTE_VALUE_VITALITY("5", "Start value of the Vitality Attribute"),
    CHARACTER_START_ATTRIBUTE_VALUE_DEXTERITY("5", "Start value of the Dexterity Attribute"),

    CHARACTER_VALUE_GROWTH_PER_LEVEL_HEALTH("25", "Additional Health per Level"),
    CHARACTER_VALUE_GROWTH_PER_LEVEL_ARMOR("20", "Addition Armor per Level"),
    CHARACTER_VALUE_GROWTH_PER_LEVEL_RESISTANCE("10", "Additional Resistance per Level"),
    CHARACTER_VALUE_GROWTH_PER_LEVEL_STAMINA("5", "Additional Stamina per Level"),
    CHARACTER_VALUE_GROWTH_PER_LEVEL_MANA("5", "Additional Mana per Level"),
    CHARACTER_VALUE_GROWTH_PER_LEVEL_ENERGY("10", "Additional Energy per Level"),

    CHARACTER_VALUE_GROWTH_PER_DEXTERITY_ARMOR("25", "Addition Armor per dexterity"),
    CHARACTER_VALUE_GROWTH_PER_DEXTERITY_RESISTANCE("5", "Additional Resistance per dexterity"),
    CHARACTER_VALUE_GROWTH_PER_VITALITY_HEALTH("5", "Additional Health per Vitality"),
    CHARACTER_VALUE_GROWTH_PER_VITALITY_STAMINA("5", "Additional Health per Vitality"),
    CHARACTER_VALUE_GROWTH_PER_INTELLIGENZ_MANA("25", "Additional Mana per Intelligenz"),

    CHARACTER_ATTRIBUTE_MIN("3", "The minimum Value of an Attribute in Character Creation"),
    CHARACTER_NAME_LENGTH_MIN("4", "Minimum Length for PlayerCharacterNames");

    private final String defaultValue;
    private String comment;

    SettingEnum (String defaultValue) {

        this.defaultValue = defaultValue;
        this.comment = "";
    }

    SettingEnum (String defaultValue, String comment) {
        this(defaultValue);
        this.comment = comment;
    }
}
