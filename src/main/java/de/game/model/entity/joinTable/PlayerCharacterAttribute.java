package de.game.model.entity.joinTable;

import de.game.model.entity.Attribute;
import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.primaryKeys.CharacterAttributeId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@IdClass(CharacterAttributeId.class)
public class PlayerCharacterAttribute {

    @Id
    @ManyToOne()
    @JoinColumn(name = "character_name")
    private PlayerCharacter characterName;

    @Id
    @ManyToOne()
    @JoinColumn(name = "attribute_key")
    private Attribute attributeKey;

    @NonNull
    private Byte value;
}
