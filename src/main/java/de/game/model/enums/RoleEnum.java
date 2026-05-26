package de.game.model.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    PLAYER(0, "Player", "Spieler der keinerlei Zugriff auf Administrative Funktionen hat", 10),
    GAMEMASTER(1, "Gamemaster", "Spieler mit beschränkten Zugriff auf Administration. Darf Account und Charakter Daten von anderen Spielern bearbeiten", 50),
    ADMIN(2, "Administrator", "Vollen Zugriff auf alle Funktionen und spiel Daten", 100);

    private final Integer id;
    private final String name;
    private final String description;
    private final Integer authorisationValue;

    RoleEnum (Integer id, String name, String description, Integer authorisationValue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authorisationValue = authorisationValue;
    }


}
