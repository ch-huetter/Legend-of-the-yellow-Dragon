package de.game.model.entity.joinTable;

import de.game.model.entity.DashboardMessage;
import de.game.model.entity.User;
import de.game.model.entity.primaryKeys.UserDashboardMessageId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@IdClass(UserDashboardMessageId.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDashboardMessage {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "dashboard_message_key", nullable = false)
    private DashboardMessage dashboardMessage;

    private Boolean dismissed;

}
