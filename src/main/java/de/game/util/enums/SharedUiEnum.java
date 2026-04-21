package de.game.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum to define Prefixes or other Values that get shared between Application/Thymeleaf/JavaScript
 * <p>
 * For Example the Prefix in the CharacterCreation for AbilityTree entries is defined here and then used by thymeleaf and by javascript
 */
@Getter
@RequiredArgsConstructor
public enum SharedUiEnum {

    CHARACTER_CREATION_ATTRIBUTE_DISPLAY_PREFIX("attribute-display-"),
    CHARACTER_CREATION_ATTRIBUTE_ARROW_LEFT_PREFIX("arrow-left-"),
    CHARACTER_CREATION_ATTRIBUTE_ARROW_RIGHT_PREFIX("arrow-right-"),
    CHARACTER_CREATION_PLAYER_ClASS_ITEM_PREFIX("player-class-"),
    CHARACTER_CREATION_PlAYER_CLASS_INPUT_ID("activePlayerClassId");

    final String value;
}
