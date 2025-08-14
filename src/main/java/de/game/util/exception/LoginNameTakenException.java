package de.game.util.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginNameTakenException extends Exception {

    public LoginNameTakenException (String message) {
        super(message);
    }

}
