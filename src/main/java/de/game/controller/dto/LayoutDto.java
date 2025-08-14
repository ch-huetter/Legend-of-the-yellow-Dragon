package de.game.controller.dto;

import de.game.view.uielements.Bar;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ToString
@Component
public class LayoutDto {
    private boolean hideHeader = false;

    private Bar healthBar;
    private Bar energyBar;
    private Bar experienceBar;

    private Integer gold;
    private Integer level;

    private List<String> messages;

}
