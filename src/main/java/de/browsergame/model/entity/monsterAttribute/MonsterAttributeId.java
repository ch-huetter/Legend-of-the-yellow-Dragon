package de.browsergame.model.entity.monsterAttribute;

import de.browsergame.model.entity.Attribute;
import de.browsergame.model.entity.Monster;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class MonsterAttributeId implements Serializable {
    private Attribute attribute;

    private Monster monster;
}
