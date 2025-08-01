package de.browsergame.model.entity;

import de.browsergame.model.entity.joinTable.PlayerCharacterAttribute;
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
    @Column(name= "attribute_key")
    private String key;
    @NonNull
    @Column(length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy="attribute")
    Set<PlayerCharacterAttribute> playerCharacterAttributeSet;

}
