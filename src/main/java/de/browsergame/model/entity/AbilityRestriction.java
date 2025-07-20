package de.browsergame.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AbilityRestriction {
    @Id
    @Column(length = 50)
    private String restrictionKey;

}
