package de.game.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ability {
    @Id
    @Column(name = "ability_key", length = 50)
    private String key;

    @NonNull
    private Integer abilityCost;

    @NonNull
    @Column(length = 50)
    private String name;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String effectDescription;

    @NonNull
    private Byte maxLevel;

    @NonNull
    private Boolean alwaysAvailable;

    @ManyToOne()
    @JoinColumn(name = "ability_type_Id")
    private AbilityType abilityType;

    @ManyToOne()
    @JoinColumn(name = "ability_resource_Id")
    private AbilityResource abilityResource;

    @OneToMany(mappedBy = "ability", orphanRemoval = true)
    @ToString.Exclude
    private Set<Effect> effects;

    @OneToMany(mappedBy = "ability", orphanRemoval = true)
    @ToString.Exclude
    private Set<AbilityAbilityTree> abilityTrees;

}
