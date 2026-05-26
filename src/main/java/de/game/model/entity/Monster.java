package de.game.model.entity;


import de.game.model.entity.joinTable.DungeonMonster;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "monster")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Monster extends LivingEntity {

    @OneToMany(mappedBy = "monster")
    @ToString.Exclude
    private Set<DungeonMonster> dungeons;

}
