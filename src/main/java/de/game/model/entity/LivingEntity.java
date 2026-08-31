package de.game.model.entity;

import jakarta.annotation.Nonnull;
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
    @Nonnull
    protected Integer baseArmor;

    @NonNull
    protected Integer resistance;
    @Nonnull
    protected Integer baseResistance;

    @NonNull
    protected Integer baseMana;
    @NonNull
    protected Integer maxMana;
    @NonNull
    protected Integer currentMana;

    @NonNull
    protected Integer baseStamina;
    @NonNull
    protected Integer maxStamina;
    @NonNull
    protected Integer currentStamina;

    @NonNull
    protected Integer baseHealth;
    @Nonnull
    protected Integer maxHealth;
    @Nonnull
    protected Integer currentHealth;

    protected short agility;
    protected short strength;
    protected short vitality;
    protected short dexterity;
    protected short intelligence;
    protected short endurance;

    @NonNull
    protected Short level;
    @NonNull
    protected Integer gold;
    @NonNull
    protected Integer experience;
    @Nonnull
    protected Short actionsPerTurn;
}
