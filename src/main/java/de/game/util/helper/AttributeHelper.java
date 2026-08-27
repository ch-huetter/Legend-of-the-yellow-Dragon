package de.game.util.helper;

import de.game.bean.dto.AttributeDto;
import de.game.model.entity.LivingEntity;
import de.game.model.enums.AttributeEnum;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.util.mapper.SettingAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AttributeHelper {

    private final SettingService settingService;
    private final SettingAttributeMapper settingAttributeMapper;

    public AttributeDto[] getAttributeArray (LivingEntity entity) {
        AttributeEnum[] values        = AttributeEnum.values();
        AttributeDto[]  attributeDTOs = new AttributeDto[values.length];

        for (int i = 0; i < values.length; i++) {
            attributeDTOs[i] = AttributeDto.builder().key(values[i].getKey()).value(values[i].getGetter().apply(entity)).build();
        }
        return attributeDTOs;
    }

    public AttributeDto[] getAttributeArray () {
        AttributeEnum[] values        = AttributeEnum.values();
        AttributeDto[]  attributeDTOs = new AttributeDto[values.length];

        for (int i = 0; i < values.length; i++) {
            attributeDTOs[i] = AttributeDto.builder().key(values[i].getKey()).build();
        }
        return attributeDTOs;
    }

    public Integer getSpendAttributePoints (LivingEntity entity) {
        Integer sum = 0;
        for (AttributeEnum attributeEnum : AttributeEnum.values()) {
            sum += calculateSpendAttributePointsForAttribute(attributeEnum, entity);
        }
        return sum;
    }

    public Integer calculateSpendAttributePointsForAttribute (String attributeKey, LivingEntity entity) {
        return calculateSpendAttributePointsForAttribute(
                Arrays.stream(AttributeEnum.values()).filter(attributeEnum -> attributeEnum.getKey().equals(attributeKey)).findFirst().orElseThrow(IllegalArgumentException::new)
                , entity);
    }

    public Integer calculateSpendAttributePointsForAttribute (AttributeEnum attributeEnum, LivingEntity entity) {
        final SettingEnum startValueSetting   = settingAttributeMapper.getStartSettingForAttributeKey(attributeEnum);
        final short       attributeStartValue = Short.parseShort(settingService.getValue(startValueSetting));
        return attributeEnum.getGetter().apply(entity) - attributeStartValue;
    }

    public Integer getAttributePointsForLevel (int level) {
        return level * 3;
    }


}
