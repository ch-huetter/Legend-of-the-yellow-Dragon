package de.game.service.factory;

import de.game.bean.ExperiencePerLevelGetter;
import de.game.model.entity.PlayerCharacter;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerCharacterFactory {

    private final SettingService settingService;
    private final PlayerCharacterAttributeFactory playerCharacterAttributeFactory;
    private final UserService userService;
    private final ExperiencePerLevelGetter experiencePerLevelGetter;

    public PlayerCharacter getNewPlayerCharacterForCharacterCreation () {
        PlayerCharacter playerCharacter = new PlayerCharacter();

        playerCharacter.setAttributes(playerCharacterAttributeFactory.createDefaultPlayerAttributes());
        playerCharacter.setAttributePoints(Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_POINTS)));

        playerCharacter.setAttributePointsSpend(0);
        playerCharacter.setLevel(Short.valueOf("1"));
        playerCharacter.setExperience(0);
        playerCharacter.setExperienceForNextLevel(experiencePerLevelGetter.getExperienceForLevel(2));
        playerCharacter.setGold(Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_GOLD)));
        playerCharacter.setUser(userService.getLoggedInUserFromDb());

        return playerCharacter;
    }

}
