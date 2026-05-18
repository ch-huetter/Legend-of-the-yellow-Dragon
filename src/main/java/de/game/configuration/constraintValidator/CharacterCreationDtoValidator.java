package de.game.configuration.constraintValidator;

import de.game.configuration.constraintValidator.annotation.ValidCharacterCreationDto;
import de.game.configuration.validator.playerCharacter.AttributeValidator;
import de.game.configuration.validator.playerCharacter.NameValidator;
import de.game.controller.dto.character.CharacterCreationDto;
import de.game.model.entity.PlayerCharacter;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.service.initializer.PlayerCharacterInitializer;
import de.game.util.helper.ValidationMessageHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CharacterCreationDtoValidator implements ConstraintValidator<ValidCharacterCreationDto, CharacterCreationDto> {

    private final AttributeValidator attributeValidator;
    private final NameValidator nameValidator;
    private final PlayerCharacterInitializer playerCharacterInitializer;
    @Value("${application.custom.default.adminAccountPrefix}")
    private String adminPrefix;
    private final SettingService settingService;
    private final ValidationMessageHelper validationMessageHelper;

    @Override
    public boolean isValid (CharacterCreationDto dto, ConstraintValidatorContext context) {
        boolean         isValid               = true;
        boolean         isNameValid           = true;
        PlayerCharacter filledPlayerCharacter = playerCharacterInitializer.initAfterCreation(dto);

        if (!nameValidator.checkLength(filledPlayerCharacter)) {
            validationMessageHelper.addFieldError(context, "name", "error.playerCharacter.name.empty",
                                                  Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_NAME_LENGTH_MIN)));
            isValid = false;
            isNameValid = false;
        }

        if (nameValidator.checkPrefix(filledPlayerCharacter)) {
            validationMessageHelper.addFieldError(context, "name", "error.playerCharacter.name.adminPrefix", adminPrefix);
            isValid = false;
            isNameValid = false;
        }
        //No unique Check required when other Name Checks already failed
        if (isNameValid && !nameValidator.checkUnique(filledPlayerCharacter)) {
            validationMessageHelper.addFieldError(context, "name", "error.playerCharacter.name.nonUnique");
            isValid = false;
        }
        //TODO Testen ob dieser Test wirklich anschlägt!
        if (!attributeValidator.validateSpentAttributePoints(filledPlayerCharacter)) {
            log.info("point Spent error");
            validationMessageHelper.addFieldError(context, "attributes", "error.playerCharacter.attribute.invalidPointAmount", (Object) null);
            isValid = false;
        }
        //TODO Testen ob dieser Test wirklich anschlägt!
        if (!attributeValidator.validateMinAttributePointLimit(filledPlayerCharacter)) {
            log.info("attribute min error");
            validationMessageHelper.addFieldError(context, "attributes", "error.playerCharacter.attribute.invalidPointValue", (Object) null);
            isValid = false;
        }

        return isValid;
    }
}
