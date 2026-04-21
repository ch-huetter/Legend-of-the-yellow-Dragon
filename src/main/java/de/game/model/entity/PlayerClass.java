package de.game.model.entity;

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
@AllArgsConstructor
@Builder
public class PlayerClass {

    @Id
    private Integer id;

    @NonNull
    private String name;

    @OneToMany()
    @ToString.Exclude
    private Set<Effect> effectSet;

    @NonNull
    private Boolean effectsInitialized;
}
