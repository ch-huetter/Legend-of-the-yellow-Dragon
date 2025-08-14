package de.game.model.entity.joinTable;

import de.game.model.entity.Attribute;
import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.primaryKeys.PlayerCharacterAttributeId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@IdClass(PlayerCharacterAttributeId.class)
public class PlayerCharacterAttribute {

    @Id
    @ManyToOne()
    @JoinColumn(name = "player_character_name")
    private PlayerCharacter playerCharacter;

    @Id
    @ManyToOne()
    @JoinColumn(name = "attribute_key")
    private Attribute attribute;

    @NonNull
    private Byte value;
}
