package de.game.controller.dto;

import de.game.view.uielements.Bar;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class LayoutDto {
    private String characterName;

    private Bar healthBar;
    private Bar energyBar;
    private Bar experienceBar;

    private Integer gold;
    private Integer level;

}
