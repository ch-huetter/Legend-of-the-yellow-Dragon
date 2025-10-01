package de.game.service.factory;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerCharacterFactory {

    private final SettingService configurationService;
    private final PlayerCharacterAttributeFactory playerCharacterAttributeFactory;

    public PlayerCharacter getNewPlayerCharacter () {
        PlayerCharacter playerCharacter = new PlayerCharacter();

        Integer startHealth = Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_HEALTH));
        playerCharacter.setHealth(startHealth);
        playerCharacter.setMaxHealth(startHealth);
        playerCharacter.setArmor(Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_ARMOR)));
        playerCharacter.setResistance(Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_RESISTANCE)));

        playerCharacter.setMaxStamina(Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_STAMINA)));
        playerCharacter.setMaxEnergy(Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_ENERGY)));
        playerCharacter.setMaxMana(Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_ENERGY)));
        playerCharacter.setMaxRage(Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_RAGE)));

        playerCharacter.setExperience(0);
        playerCharacter.setLevel(Short.valueOf("5"));
        playerCharacter.setGold(Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_GOLD)));

        playerCharacter.setAttributes(playerCharacterAttributeFactory.createDefaultPlayerAttributes());

        playerCharacter.setAttributePoints(Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_POINTS)));
        playerCharacter.setAbilityPoints(Integer.parseInt(configurationService.getValue(SettingEnum.CHARACTER_START_ABILITY_POINTS)));
        playerCharacter.setAbilityTreePoints(1);

        playerCharacter.setAbilityPointsSpend(0);
        playerCharacter.setAttributePointsSpend(0);
        playerCharacter.setAbilityTreePointsSpend(1);

        playerCharacter.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return playerCharacter;
    }

}
