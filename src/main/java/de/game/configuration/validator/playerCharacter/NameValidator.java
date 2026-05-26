package de.game.configuration.validator.playerCharacter;

import de.game.model.entity.PlayerCharacter;
import de.game.model.enums.SettingEnum;
import de.game.model.repository.PlayerCharacterRepository;
import de.game.service.SettingService;
import de.game.util.basic.BasicEmptyCheck;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
@Slf4j
public class NameValidator {

    private final SettingService settingService;
    private final PlayerCharacterRepository playerCharacterRepository;
    @Value("${application.custom.default.adminAccountPrefix}")
    private String adminAccountPrefix;

    public boolean checkPrefix (PlayerCharacter playerCharacter) {
        return playerCharacter.getName().startsWith(adminAccountPrefix);
    }

    public boolean checkLength (PlayerCharacter playerCharacter) {
        String name      = playerCharacter.getName();
        int    minLength = Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_NAME_LENGTH_MIN));

        if (!BasicEmptyCheck.isSet(name)) {
            log.info("Name not set");
            return false;
        }

        if (name.length() < minLength) {
            log.info("Name to short");
            return false;
        }

        return true;

    }

    /**
     * Checks if a Character with that Name already exists in Database
     *
     * @param playerCharacter
     * @return Returns True when none is found
     */
    public boolean checkUnique (PlayerCharacter playerCharacter) {
        return !playerCharacterRepository.existsByName(playerCharacter.getName());
    }

}
