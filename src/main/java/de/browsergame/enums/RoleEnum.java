package de.browsergame.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    PLAYER (0, "player", "Spieler der keinerlei Zugriff auf Administrative Funktionen hat"),
    GAMEMASTER(1,  "gamemaster" , "Spieler mit beschränkten Zugriff auf Administration. Darf Account und Charakter Daten von anderen Spielern bearbeiten"),
    ADMIN(2,"Administrator", "Vollen Zugriff auf alle Funktionen und spiel Daten");

    private final Integer id;
    private final String name;
    private final String description;

    private RoleEnum(Integer id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }


}
