package de.game.util.basic;

import org.springframework.stereotype.Component;

@Component
public class BasicStringCheck {
    public static Boolean isSet (String text) {
        return text != null && !text.isBlank();
    }
}
