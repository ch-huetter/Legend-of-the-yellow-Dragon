package de.game.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Effect {

    @NonNull
    private Integer id;

    @NonNull
    @Column(length = 50)
    @Id
    private String name;

}
