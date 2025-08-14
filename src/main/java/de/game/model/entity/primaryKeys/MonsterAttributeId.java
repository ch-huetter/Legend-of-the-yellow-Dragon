package de.game.model.entity.primaryKeys;

import de.game.model.entity.Attribute;
import de.game.model.entity.Monster;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class MonsterAttributeId implements Serializable {
    private Attribute attribute;

    private Monster monster;
}
