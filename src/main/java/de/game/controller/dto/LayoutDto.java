package de.game.controller.dto;

import de.game.model.entity.PlayerCharacter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class LayoutDto {
    PlayerCharacter playerCharacter;

}
