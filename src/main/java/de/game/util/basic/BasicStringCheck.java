package de.game.util.basic;

public class BasicStringCheck {
    public static Boolean isSet (String text) {
        return text != null && !text.isBlank();
    }
}
