package de.game.model.entity.primaryKeys;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class MonsterAttributeId implements Serializable {

    private String attribute;

    private String monster;
}
