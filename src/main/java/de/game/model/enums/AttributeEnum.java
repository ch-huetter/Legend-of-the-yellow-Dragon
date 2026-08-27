package de.game.model.enums;

import de.game.model.entity.LivingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiConsumer;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum AttributeEnum {

    AGILITY("agility", LivingEntity::getAgility, LivingEntity::setAgility),
    STRENGTH("strength", LivingEntity::getStrength, LivingEntity::setStrength),
    VITALITY("vitality", LivingEntity::getVitality, LivingEntity::setVitality),
    DEXTERITY("dexterity", LivingEntity::getDexterity, LivingEntity::setDexterity),
    INTELLIGENCE("intelligence", LivingEntity::getIntelligence, LivingEntity::setIntelligence),
    ENDURANCE("endurance", LivingEntity::getEndurance, LivingEntity::setEndurance);
    
    private final String key;
    private final Function<LivingEntity, Short> getter;
    private final BiConsumer<LivingEntity, Short> setter;
}
