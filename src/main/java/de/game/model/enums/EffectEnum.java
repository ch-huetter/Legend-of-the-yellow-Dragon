package de.game.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EffectEnum {

    ATTRIBUTE_BONUS_VITALITY(4, "attributeBonus-Vitality"),
    ATTRIBUTE_BONUS_AGILITY(3, "attributeBonus-Agility"),
    ATTRIBUTE_BONUS_INTELLIGENCE(2, "attributeBonus-Intelligence"),
    ATTRIBUTE_BONUS_STRENGTH(1, "attributeBonus-Strength"),
    ATTRIBUTE_BONUS_DEXTERITY(0, "attributeBonus-Dexterity");

    private final Integer id;
    private final String name;

}
