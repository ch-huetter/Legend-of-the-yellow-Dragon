package de.game.model.entity.joinTable;

import de.game.model.entity.DashboardMessage;
import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.primaryKeys.PlayerCharacterDashboardMessageId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@IdClass(PlayerCharacterDashboardMessageId.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCharacterDashboardMessage {

    @Id
    @ManyToOne()
    @JoinColumn(name = "dashboard_message_key")
    DashboardMessage dashboardMessage;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_character_id")
    PlayerCharacter playerCharacter;

    Boolean dismissed;

}
