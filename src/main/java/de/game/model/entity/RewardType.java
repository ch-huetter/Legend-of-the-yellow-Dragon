package de.game.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class RewardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rewardKey", length = 25)
    private String key;

    //TODO Koppeltabelle für Level und reward
    //TODO Enum für Reward Typen
}
