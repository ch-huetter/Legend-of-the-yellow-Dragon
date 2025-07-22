package de.browsergame.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AbilityTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @ManyToOne()
    @JoinColumn(name="ability_key")
    private Ability ability;
    @NonNull
    private Byte priority;
    @NonNull
    private Byte tier;


}
