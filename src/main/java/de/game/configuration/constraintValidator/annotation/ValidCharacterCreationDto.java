package de.game.configuration.constraintValidator.annotation;

import de.game.configuration.constraintValidator.CharacterCreationDtoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CharacterCreationDtoValidator.class)
public @interface ValidCharacterCreationDto {
    String message () default "Ungültige Charakter Daten";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};

}
