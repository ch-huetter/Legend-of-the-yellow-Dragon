package de.game.service.factory.livingEntity;

import de.game.bean.dto.LivingEntityDto;
import de.game.model.entity.LivingEntity;

public abstract class LivingEntityDtoFactory {

    protected void fillLivingEntityFields (LivingEntityDto livingEntityDto, LivingEntity livingEntity) {
        livingEntityDto.setName(livingEntity.getName());
        livingEntityDto.setMaxHealth(livingEntity.getMaxHealth());
        livingEntityDto.setBaseHealth(livingEntity.getBaseHealth());
        livingEntityDto.setCurrentHealth(livingEntity.getCurrentHealth());
        livingEntityDto.setArmor(livingEntity.getArmor());
        livingEntityDto.setBaseArmor(livingEntity.getBaseArmor());
        livingEntityDto.setGold(livingEntity.getGold());
        livingEntityDto.setLevel(livingEntity.getLevel());
        livingEntityDto.setExperience(livingEntity.getExperience());
    }
}
