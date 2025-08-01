package de.browsergame.model.entity.joinTable;

import de.browsergame.model.entity.Role;
import de.browsergame.model.entity.User;
import de.browsergame.model.entity.primaryKeys.UserRoleId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@IdClass(UserRoleId.class)
@NoArgsConstructor
public class UserRole {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
