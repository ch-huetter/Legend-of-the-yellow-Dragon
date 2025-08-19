package de.game.configuration.constraintValidator;

import de.game.configuration.annotation.ValidRegistrationDTO;
import de.game.configuration.validator.UserValidator;
import de.game.controller.dto.RegistrationDto;
import de.game.model.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RegistrationDTOConstraintValidator implements ConstraintValidator<ValidRegistrationDTO, RegistrationDto> {

    private final UserValidator userValidator;

    @Override
    public boolean isValid (RegistrationDto value, ConstraintValidatorContext context) {
        log.info("Starting RegistrationDTOConstraintValidator");
        final User user    = value.getUser();
        boolean    isValid = true;

        if (userValidator.validateLoginName(user)) {
            log.debug("loginName Constraint found");
            context.buildConstraintViolationWithTemplate("{error.user.nonUniqueLoginName}").addPropertyNode("user.loginName").addConstraintViolation();
            isValid = false;
        } else {
            log.debug("no loginname constraint found");
        }

        if (userValidator.validatePasswordLength(user)) {
            log.debug("password constraint found for password {}", user.getPassword());
            context.buildConstraintViolationWithTemplate("{error.user.passwordLength}").addPropertyNode("user.password").addConstraintViolation();
            isValid = false;
        } else {
            log.debug("no password constraint found for password {}", user.getPassword());
        }

        if (userValidator.validateEmailAddressExists(user)) {
            log.debug("Email Constraint found for email {}", user.getEMail());
            context.buildConstraintViolationWithTemplate("{error.user.emailAddressTaken}").addPropertyNode("user.eMail").addConstraintViolation();
            isValid = false;
        } else {
            log.debug("No Email Constraint found for email {}", user.getEMail());
        }


        log.info("Dto is valid = {}", isValid);
        return isValid;
    }
}
