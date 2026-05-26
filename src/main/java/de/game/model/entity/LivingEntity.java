package de.game.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * Base Class for all Character Based Entity's like PlayerCharacters and Monsters. Because they have more in common than you think
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@ToString
public abstract class LivingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Integer id;

    @Column(length = 30)
    protected String name;

    @NonNull
    protected Integer armor;
    @NonNull
    protected Integer resistance;
    @NonNull
    protected Integer maxMana;
    @NonNull
    protected Integer maxStamina;
    @NonNull
    protected Integer maxHealth;

    @NonNull
    protected Short level;
    @NonNull
    protected Integer gold;
    @NonNull
    protected Integer experience;

}
