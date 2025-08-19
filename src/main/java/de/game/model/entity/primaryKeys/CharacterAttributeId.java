package de.game.model.entity.primaryKeys;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CharacterAttributeId {

    private String characterName;
    private String attributeKey;
}
