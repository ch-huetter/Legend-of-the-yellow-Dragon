package de.game.model.entity.primaryKeys;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class DungeonMonsterId {

    private Integer dungeon;
    private String monster;
    private Byte wave;
}
