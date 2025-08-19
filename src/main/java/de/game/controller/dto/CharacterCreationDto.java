package de.game.controller.dto;

import de.game.model.entity.PlayerCharacter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CharacterCreationDto {

    PlayerCharacter playerCharacter;

}
