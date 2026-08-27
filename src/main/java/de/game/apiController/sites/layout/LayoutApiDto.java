package de.game.apiController.sites.layout;

import de.game.apiController.AbstractApiDto;
import de.game.bean.dto.PlayerCharacterDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class LayoutApiDto extends AbstractApiDto {
    private PlayerCharacterDto playerCharacterDto;
}
