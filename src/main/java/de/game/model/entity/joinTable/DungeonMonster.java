package de.game.model.entity.joinTable;

import de.game.model.entity.Dungeon;
import de.game.model.entity.Monster;
import de.game.model.entity.primaryKeys.DungeonMonsterId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@IdClass(DungeonMonsterId.class)
public class DungeonMonster {

    @Id
    @ManyToOne()
    @JoinColumn(name = "dungeon_id")
    @ToString.Exclude
    private Dungeon dungeon;

    @Id
    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;

    @Id
    private Byte wave;

    @NonNull
    private Byte chance;

}
