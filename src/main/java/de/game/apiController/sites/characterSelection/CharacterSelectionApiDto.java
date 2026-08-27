package de.game.apiController.sites.characterSelection;

import de.game.apiController.AbstractApiDto;
import de.game.bean.dto.PlayerCharacterDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class CharacterSelectionApiDto extends AbstractApiDto {

    private List<PlayerCharacterDto> playerCharacterList;

}

