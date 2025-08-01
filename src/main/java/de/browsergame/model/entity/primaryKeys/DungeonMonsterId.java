package de.browsergame.model.entity.primaryKeys;

import de.browsergame.model.entity.Dungeon;
import de.browsergame.model.entity.Monster;
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
