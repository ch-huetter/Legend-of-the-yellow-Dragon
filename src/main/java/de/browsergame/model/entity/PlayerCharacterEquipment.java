package de.browsergame.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlayerCharacterEquipment {

    @Id
    Integer id;

    @ManyToOne
    @JoinColumn(name="playerCharacter_id")
    private PlayerCharacter playerCharacter;

    @ManyToOne
    @JoinColumn(name="equipmentSlot_id")
    private EquipmentSlot slot;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

}
