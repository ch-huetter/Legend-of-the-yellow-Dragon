package de.game.model.entity;

import de.game.model.entity.joinTable.CharacterAbilityTree;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbilityTree {

    @Id
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String description;

    @OneToMany(mappedBy = "abilityTree")
    @ToString.Exclude
    private Set<CharacterAbilityTree> abilityTrees;


}
