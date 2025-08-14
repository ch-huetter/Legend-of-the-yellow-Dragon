package de.game.configuration.annotation;

import de.game.configuration.constraintValidator.RegistrationDTOConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegistrationDTOConstraintValidator.class)
public @interface ValidRegistrationDTO {
    String message () default "Ungültige Nutzerdaten";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};


}

