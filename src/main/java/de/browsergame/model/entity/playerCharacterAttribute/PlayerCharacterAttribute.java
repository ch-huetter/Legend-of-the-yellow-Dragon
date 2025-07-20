package de.browsergame.model.entity.playerCharacterAttribute;

import de.browsergame.model.entity.Attribute;
import de.browsergame.model.entity.PlayerCharacter;
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
