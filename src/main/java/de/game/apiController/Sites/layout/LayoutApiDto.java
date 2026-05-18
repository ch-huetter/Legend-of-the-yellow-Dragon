package de.game.apiController.Sites.layout;

import de.game.apiController.AbstractApiDto;
import de.game.model.entity.PlayerCharacter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class LayoutApiDto extends AbstractApiDto {
    PlayerCharacter playerCharacter;
}
