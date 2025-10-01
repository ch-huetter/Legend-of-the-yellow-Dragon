package de.game.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttributeEnum {

    AGILITY("agility"),
    STRENGTH("strength"),
    VITALITY("vitality"),
    DEXTERITY("dexterity"),
    INTELLIGENCE("intelligence");

    private final String key;

}
