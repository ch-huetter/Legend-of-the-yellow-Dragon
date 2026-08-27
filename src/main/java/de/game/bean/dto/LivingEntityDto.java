package de.game.bean.dto;

import de.game.model.entity.LivingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class LivingEntityDto {

    protected LivingEntityDto (LivingEntity livingEntity) {

    }

    protected String name;

    protected Integer armor;
    protected Integer baseArmor;

    protected Integer resistance;
    protected Integer baseResistance;

    protected Integer currentHealth;
    protected Integer maxHealth;
    protected Integer baseHealth;

    protected Short level;
    protected Integer gold;
    protected Integer experience;

}
