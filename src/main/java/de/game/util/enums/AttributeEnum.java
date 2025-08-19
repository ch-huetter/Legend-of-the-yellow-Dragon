package de.game.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttributeEnum {

    AGILITY("agility"),
    STRENGTH("Strength"),
    VITALITY("vitality"),
    DEXTERITY("dexterity"),
    INTELLIGENCE("intelligence");

    private final String name;

}
