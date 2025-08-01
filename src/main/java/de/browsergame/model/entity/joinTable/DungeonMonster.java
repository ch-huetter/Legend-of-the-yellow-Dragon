package de.browsergame.model.entity.joinTable;

import de.browsergame.model.entity.Dungeon;
import de.browsergame.model.entity.Monster;
import de.browsergame.model.entity.primaryKeys.DungeonMonsterId;
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
