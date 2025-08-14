package de.game.model.entity.joinTable;

import de.game.model.entity.Attribute;
import de.game.model.entity.Monster;
import de.game.model.entity.primaryKeys.MonsterAttributeId;
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
    @JoinColumn(name = "monster_id")
    private Monster monster;

    @Id
    @ManyToOne()
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @NonNull
    private Byte priority;


}
