package de.game.util.attribute.valueCalculator;

import de.game.model.entity.PlayerCharacter;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.util.helper.AttributeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerCharacterValueCalculator {

    private final SettingService settingService;
    private final AttributeHelper attributeHelper;

    public void calculateEntityValues (PlayerCharacter entity) {
        calculateStatusValues(entity);
        calculateAttributePoints(entity);
    }

    public void calculateAttributePoints (PlayerCharacter playerCharacter) {
        Integer attributePointsSpent    = attributeHelper.getSpendAttributePoints(playerCharacter);
        Integer attributePointsForLevel = attributeHelper.getAttributePointsForLevel(Integer.parseInt(playerCharacter.getLevel().toString()));

        playerCharacter.setAttributePointsSpend(attributePointsSpent);
        playerCharacter.setAttributePoints(attributePointsForLevel - attributePointsSpent);

    }

    public void calculateStatusValues (PlayerCharacter playerCharacter) throws IllegalArgumentException {
        Integer newHealth = calculateHealth(playerCharacter);
        playerCharacter.setBaseHealth(newHealth);
        playerCharacter.setMaxHealth(newHealth);
        playerCharacter.setCurrentHealth(newHealth);

        Integer newStamina = calculateStamina(playerCharacter);
        playerCharacter.setBaseStamina(newStamina);
        playerCharacter.setMaxStamina(newStamina);
        playerCharacter.setCurrentStamina(newStamina);

        Integer newMana = calculateMana(playerCharacter);
        playerCharacter.setBaseMana(newMana);
        playerCharacter.setMaxMana(newMana);
        playerCharacter.setCurrentMana(newMana);

        Integer newArmor = calculateArmor(playerCharacter);
        playerCharacter.setBaseArmor(newArmor);
        playerCharacter.setArmor(newArmor);

        Integer newResistance = calculateResistance(playerCharacter);
        playerCharacter.setBaseResistance(newResistance);
        playerCharacter.setResistance(newResistance);
    }

    public Integer calculateHealth (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_HEALTH)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_VITALITY_HEALTH)) *
                playerCharacter.getVitality()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_HEALTH)) * (playerCharacter.getLevel() - 1));
    }

    public Integer calculateStamina (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_STAMINA)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_ENDURANCE_STAMINA)) *
                playerCharacter.getEndurance()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_STAMINA)) * (playerCharacter.getLevel() - 1));
    }

    public Integer calculateMana (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_MANA)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_INTELLIGENZ_MANA)) *
                playerCharacter.getIntelligence()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_MANA)) * (playerCharacter.getLevel() - 1));
    }

    public Integer calculateArmor (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_ARMOR)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_DEXTERITY_ARMOR)) *
                playerCharacter.getDexterity()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_ARMOR)) * (playerCharacter.getLevel() - 1));
    }

    public Integer calculateResistance (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_RESISTANCE)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_DEXTERITY_RESISTANCE)) *
                playerCharacter.getDexterity()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_RESISTANCE)) * (playerCharacter.getLevel() - 1));
    }

}
