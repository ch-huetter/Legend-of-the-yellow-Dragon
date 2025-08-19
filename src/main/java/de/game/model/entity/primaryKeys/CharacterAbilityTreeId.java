package de.game.model.entity.primaryKeys;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CharacterAbilityTreeId implements Serializable {

    String character;

    Integer abilityTree;
}
