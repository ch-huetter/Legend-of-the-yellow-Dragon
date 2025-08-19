package de.game.model.entity;

import de.game.model.entity.joinTable.PlayerCharacterAttribute;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Attribute {

    @Id
    @Column(name = "attribute_key")
    private String key;
    @NonNull
    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "attributeKey")
    @ToString.Exclude
    Set<PlayerCharacterAttribute> playerCharacterAttributeSet;

}
