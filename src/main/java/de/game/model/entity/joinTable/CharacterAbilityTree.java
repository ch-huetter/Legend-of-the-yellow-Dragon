package de.game.model.entity.joinTable;

import de.game.model.entity.AbilityTree;
import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.primaryKeys.CharacterAbilityTreeId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@IdClass(CharacterAbilityTreeId.class)
public class CharacterAbilityTree {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "character_name", nullable = false)
    PlayerCharacter character;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "abilityTree_id", nullable = false)
    AbilityTree abilityTree;

}
