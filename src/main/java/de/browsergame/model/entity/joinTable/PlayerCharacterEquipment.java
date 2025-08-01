package de.browsergame.model.entity.joinTable;

import de.browsergame.model.entity.EquipmentSlot;
import de.browsergame.model.entity.Item;
import de.browsergame.model.entity.PlayerCharacter;
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
public class PlayerCharacterEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
