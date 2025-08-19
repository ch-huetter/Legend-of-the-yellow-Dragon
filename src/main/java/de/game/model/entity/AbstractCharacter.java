package de.game.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * Base Class for all Character Based Entity's like PlayerCharacters and Monsters. Because they have more in common than you think
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractCharacter {
    @Id
    @Column(length = 50)
    private String name;

    @NonNull
    private Integer armor;
    @NonNull
    private Integer resistance;
    @NonNull
    private Integer maxMana;
    @NonNull
    private Integer maxRage;
    @NonNull
    private Integer maxStamina;
    @NonNull
    private Integer maxHealth;

    @NonNull
    private Short level;
    @NonNull
    private Integer gold;
    @NonNull
    private Integer experience;
}
