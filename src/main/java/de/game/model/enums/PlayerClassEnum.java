package de.game.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlayerClassEnum {

    WARRIOR(0, "Warrior"),
    MAGE(1, "Mage"),
    ARCHER(2, "Archer");

    private final Integer id;
    private final String name;


}
