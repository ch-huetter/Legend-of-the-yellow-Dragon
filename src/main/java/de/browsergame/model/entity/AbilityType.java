package de.browsergame.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class AbilityType {
    @Id
    private Integer id;

    @NonNull
    @Column(length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

}
