package de.game.model.entity.primaryKeys;

import de.game.model.entity.Attribute;
import de.game.model.entity.PlayerCharacter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class PlayerCharacterAttributeId {

    private PlayerCharacter playerCharacter;
    private Attribute attribute;
}
