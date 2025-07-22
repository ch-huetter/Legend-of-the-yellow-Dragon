package de.browsergame.model.entity;

import de.browsergame.model.entity.dungeonMonster.DungeonMonster;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dungeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(length = 50)
    private String name;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String description;
    @NonNull
    private Byte levelMin;
    @NonNull
    private Byte levelMax;

    @NonNull
    private Byte monsterQuantityMin;
    @NonNull
    private Byte monsterQuantityMax;

    @NonNull
    private Byte waves;

    @OneToMany(mappedBy = "dungeon")
    private Set<DungeonMonster> monsters;

}
