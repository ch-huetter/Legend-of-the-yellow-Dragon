package de.game.util.attribute.valueCalculator;

import de.game.model.entity.PlayerCharacter;
import de.game.model.enums.AttributeEnum;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.util.helper.PlayerCharacterAttributeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterValueCalculator implements AttributeValueCalculator<PlayerCharacter> {

    private final SettingService settingService;
    private final PlayerCharacterAttributeHelper playerCharacterAttributeHelper;

    @Override
    public void calculateEntityValues (PlayerCharacter entity) {
        calculateStatusValues(entity);
        calculateAttributePoints(entity);
    }

    public void calculateAttributePoints (PlayerCharacter playerCharacter) {
        Integer attributePointsSpent    = playerCharacterAttributeHelper.calculateSpendAttributePoints(playerCharacter);
        Integer attributePointsForLevel = playerCharacterAttributeHelper.getAttributePointsForLevel(Integer.valueOf(playerCharacter.getLevel().toString()));

        playerCharacter.setAttributePointsSpend(attributePointsSpent);
        playerCharacter.setAttributePoints(attributePointsForLevel - attributePointsSpent);

    }

    public void calculateStatusValues (PlayerCharacter playerCharacter) throws IllegalArgumentException {
        Integer newHealth = calculateHealth(playerCharacter);

        playerCharacter.setMaxHealth(newHealth);
        playerCharacter.setHealth(newHealth);

        Integer newStamina = calculateStamina(playerCharacter);
        playerCharacter.setMaxStamina(newStamina);

        Integer newMana = calculateMana(playerCharacter);

        playerCharacter.setMaxMana(newMana);

        Integer newEnergy = calculateEnergy(playerCharacter);
        playerCharacter.setMaxEnergy(newEnergy);
        playerCharacter.setEnergy(newEnergy);

        Integer newArmor = calculateArmor(playerCharacter);
        playerCharacter.setArmor(newArmor);

        Integer newResistance = calculateResistence(playerCharacter);
        playerCharacter.setResistance(newResistance);

    }

    public Integer calculateHealth (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_HEALTH)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_VITALITY_HEALTH)) *
                playerCharacterAttributeHelper.findAttributeFromList(playerCharacter.getAttributes(),
                                                                     AttributeEnum.VITALITY).getValue()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_HEALTH)) * (playerCharacter.getLevel() - 1));
    }

    public Integer calculateStamina (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_STAMINA)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_VITALITY_STAMINA)) *
                playerCharacterAttributeHelper.findAttributeFromList(playerCharacter.getAttributes(), AttributeEnum.VITALITY).getValue()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_STAMINA)) * (playerCharacter.getLevel() - 1));
    }

    public Integer calculateMana (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_MANA)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_INTELLIGENZ_MANA)) *
                playerCharacterAttributeHelper.findAttributeFromList(playerCharacter.getAttributes(), AttributeEnum.INTELLIGENCE).getValue()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_MANA)) * (playerCharacter.getLevel() - 1));
    }

    public Integer calculateEnergy (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_ENERGY)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_ENERGY)) * (playerCharacter.getLevel() - 1));
    }

    public Integer calculateArmor (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_ARMOR)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_DEXTERITY_ARMOR)) *
                playerCharacterAttributeHelper.findAttributeFromList(playerCharacter.getAttributes(), AttributeEnum.DEXTERITY).getValue()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_ARMOR)) * (playerCharacter.getLevel() - 1));
    }

    public Integer calculateResistence (PlayerCharacter playerCharacter) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_RESISTANCE)) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_DEXTERITY_RESISTANCE)) *
                playerCharacterAttributeHelper.findAttributeFromList(playerCharacter.getAttributes(), AttributeEnum.DEXTERITY).getValue()) +
               (Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_VALUE_GROWTH_PER_LEVEL_RESISTANCE)) * (playerCharacter.getLevel() - 1));
    }

    @Override
    public Class<PlayerCharacter> getSupportedClass () {
        return PlayerCharacter.class;
    }

}
