package de.browsergame.model.entity.dungeonMonster;

import de.browsergame.model.entity.Dungeon;
import de.browsergame.model.entity.Monster;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class DungeonMonsterId {

    private Dungeon dungeon;
    private Monster monster;
    private Byte wave;
}
