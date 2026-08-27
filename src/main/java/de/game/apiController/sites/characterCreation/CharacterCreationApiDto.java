package de.game.apiController.sites.characterCreation;

import de.game.apiController.AbstractApiDto;
import de.game.bean.frontendComponents.AttributeSelectionEntry;
import de.game.model.enums.PlayerClassEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class CharacterCreationApiDto extends AbstractApiDto {
    private List<PlayerClassEnum> playerClasses;
    private List<AttributeSelectionEntry> attributeSelectionEntries;
    private Short attributePoints;
    private Short activePlayerClass;
}
