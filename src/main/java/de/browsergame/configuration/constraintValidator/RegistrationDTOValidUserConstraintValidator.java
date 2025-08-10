package de.browsergame.configuration.constraintValidator;

import de.browsergame.configuration.annotation.ValidRegistrationDTO;
import de.browsergame.model.entity.User;
import de.browsergame.model.entity.dto.RegistrationDTO;
import de.browsergame.model.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrationDTOValidUserConstraintValidator implements ConstraintValidator<ValidRegistrationDTO, RegistrationDTO> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid (RegistrationDTO value, ConstraintValidatorContext context) {
        final String basePath = "registrationDTO";
        final User   user     = value.getUser();

        boolean isValid = true;

        if (userRepository.existByLoginName(user.getLoginName())) {
            context.buildConstraintViolationWithTemplate("{error.user.nonUniqueLoginName}").addPropertyNode("user.loginName").addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}
