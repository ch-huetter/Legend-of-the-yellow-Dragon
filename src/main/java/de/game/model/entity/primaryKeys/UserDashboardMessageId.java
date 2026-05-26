package de.game.model.entity.primaryKeys;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class UserDashboardMessageId {

    private String dashboardMessage;

    private Integer user;

}
