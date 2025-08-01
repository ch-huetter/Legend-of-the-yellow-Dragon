package de.browsergame.model.entity.primaryKeys;

import de.browsergame.model.entity.Attribute;
import de.browsergame.model.entity.PlayerCharacter;
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
