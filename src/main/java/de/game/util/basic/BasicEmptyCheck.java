package de.game.util.basic;

import org.springframework.stereotype.Component;

@Component
public class BasicEmptyCheck {

    /**
     * @param text to check
     * @return true if the text is not null and not empty or full of whitespaces
     */
    public static Boolean isSet (String text) {
        return text != null && !text.isBlank();
    }


    /**
     * @param number to check
     * @return true if a number is not null and not 0.
     */
    public static Boolean isSet (Integer number) {
        return number != null && number != 0;
    }

}
