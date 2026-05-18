package de.game.apiController.Sites.characterSelection;

import de.game.apiController.AbstractApiDto;
import de.game.model.entity.PlayerCharacter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class CharacterSelectionApiDto extends AbstractApiDto {

    private List<PlayerCharacter> playerCharacterList;

}

