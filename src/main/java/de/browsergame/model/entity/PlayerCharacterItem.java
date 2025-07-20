package de.browsergame.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlayerCharacterItem {
    @Id
    private Integer id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="character_name", referencedColumnName = "name")
    private PlayerCharacter playerCharacter;

    @ManyToOne
    @JoinColumn(name="item_id", referencedColumnName = "id")
    private Item item;





}
