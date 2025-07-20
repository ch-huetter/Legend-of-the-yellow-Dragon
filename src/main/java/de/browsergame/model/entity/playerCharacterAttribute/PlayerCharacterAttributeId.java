package de.browsergame.model.entity.playerCharacterAttribute;

import de.browsergame.model.entity.Attribute;
import de.browsergame.model.entity.PlayerCharacter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class PlayerCharacterAttributeId {

    private PlayerCharacter playerCharacter;
    private Attribute attribute;
}
