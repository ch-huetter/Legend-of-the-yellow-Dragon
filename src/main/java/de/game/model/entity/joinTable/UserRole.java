package de.game.model.entity.joinTable;

import de.game.model.entity.Role;
import de.game.model.entity.User;
import de.game.model.entity.primaryKeys.UserRoleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
