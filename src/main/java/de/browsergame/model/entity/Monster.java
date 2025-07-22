package de.browsergame.model.entity;


import de.browsergame.model.entity.dungeonMonster.DungeonMonster;
import de.browsergame.model.entity.monsterAttribute.MonsterAttribute;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(length = 50)
    private String name;
    @NonNull
    private Byte level;

    //Stats
    @NonNull
    private Byte strength;
    @NonNull
    private Byte vitality;
    @NonNull
    private Byte dexterity;
    @NonNull
    private Byte agility;
    @NonNull
    private Byte intelligence;
    @NonNull
    private Byte maxStamina;
    @NonNull
    private Byte maxMana;
    @NonNull
    private Byte rage;
    @NonNull
    private Byte armor;
    @NonNull
    private Byte resistance;
    @NonNull
    private Byte health;

    //Rewards
    @NonNull
    private Byte gold;
    @NonNull
    private Byte experience;

    @OneToMany(mappedBy = "monster")
    private Set<DungeonMonster> dungeons;

    @OneToMany(mappedBy = "monster")
    private Set<MonsterAttribute> attributes;

}
