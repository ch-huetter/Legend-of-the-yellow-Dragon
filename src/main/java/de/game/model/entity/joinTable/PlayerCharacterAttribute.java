package de.game.model.entity.joinTable;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Builder
@AllArgsConstructor
@IdClass(PlayerCharacterAttributeId.class)
public class PlayerCharacterAttribute {

    @Id
    @ManyToOne()
    @JoinColumn(name = "character_id")
    @ToString.Exclude
    @JsonBackReference
    private PlayerCharacter playerCharacter;

    @Id
    @ManyToOne()
    @JoinColumn(name = "attribute_key")
    private Attribute attribute;

    @NonNull
    private Short value;
}
