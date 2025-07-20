package de.browsergame.model.entity.monsterAttribute;

import de.browsergame.model.entity.Attribute;
import de.browsergame.model.entity.Monster;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@IdClass(MonsterAttributeId.class)
public class MonsterAttribute {

    @Id
    @ManyToOne()
    @JoinColumn(name="monster_id")
    private Monster monster;

    @Id
    @ManyToOne()
    @JoinColumn(name="attribute_id")
    private Attribute attribute;

    @NonNull
    private Byte priority;


}
