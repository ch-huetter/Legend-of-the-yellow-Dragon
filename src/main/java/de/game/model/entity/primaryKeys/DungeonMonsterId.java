package de.game.model.entity.primaryKeys;

import de.game.model.entity.Dungeon;
import de.game.model.entity.Monster;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class DungeonMonsterId {

    private Dungeon dungeon;
    private Monster monster;
    private Byte wave;
}
