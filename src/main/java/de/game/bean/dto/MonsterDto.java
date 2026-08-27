package de.game.bean.dto;


import de.game.model.entity.Monster;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MonsterDto extends LivingEntityDto {

    public MonsterDto (Monster monster) {
        super(monster);
    }
    
}
