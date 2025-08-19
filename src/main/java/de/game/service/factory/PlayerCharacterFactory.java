package de.game.service.factory;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.service.SettingService;
import de.game.util.enums.SettingEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PlayerCharacterFactory {

    private final SettingService configurationService;
    private final PlayerCharacterAttributeFactory playerCharacterAttributeFactory;

    public PlayerCharacter getNewPlayerCharacter () {
        PlayerCharacter playerCharacter = new PlayerCharacter();

        Integer startHealth = configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_HEALTH);
        playerCharacter.setHealth(startHealth);

        playerCharacter.setMaxHealth(startHealth);
        playerCharacter.setArmor(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_ARMOR));
        playerCharacter.setResistance(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_RESISTANCE));

        playerCharacter.setMaxStamina(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_STAMINA));
        playerCharacter.setMaxEnergy(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_ENERGY));
        playerCharacter.setMaxMana(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_ENERGY));
        playerCharacter.setMaxRage(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_RAGE));

        playerCharacter.setExperience(0);
        playerCharacter.setLevel(Short.valueOf("5"));
        playerCharacter.setGold(configurationService.getValue(SettingEnum.CHARACTER_START_GOLD));

        playerCharacter.setAttributes(playerCharacterAttributeFactory.createDefaultPlayerAttributes());

        playerCharacter.setAttributePoints(configurationService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_POINTS));
        playerCharacter.setAbilityPoints(configurationService.getValue(SettingEnum.CHARACTER_START_ABILITY_POINTS));
        playerCharacter.setAbilityTreePoints(1);
        //TODO Neue Player Character Werte hier mit übernehmen

        playerCharacter.setAbilityPointsSpend(0);
        playerCharacter.setAttributePointsSpend(0);
        playerCharacter.setAbilityTreePointsSpend(1);

        playerCharacter.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        playerCharacter.setInventory(new ArrayList<>());
        playerCharacter.setEquipmentList(new ArrayList<>());
        return playerCharacter;
    }

}
