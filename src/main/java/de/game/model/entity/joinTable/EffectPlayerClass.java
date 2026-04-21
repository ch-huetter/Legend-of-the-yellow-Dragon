package de.game.model.entity.joinTable;

import de.game.model.entity.Effect;
import de.game.model.entity.PlayerClass;
import de.game.model.entity.primaryKeys.EffectPlayerClassId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@IdClass(EffectPlayerClassId.class)
public class EffectPlayerClass {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "player_class_id", nullable = false)
    private PlayerClass playerClass;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "effect_id", nullable = false)
    private Effect effect;

    @NonNull
    private Short value;
}
