package de.game.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlayerClassEnum {

    WARRIOR(0, "warrior"),
    MAGE(1, "mage"),
    DUELIST(2, "duelist"),
    MONK(3, "monk");

    private final Integer id;
    private final String name;


}
