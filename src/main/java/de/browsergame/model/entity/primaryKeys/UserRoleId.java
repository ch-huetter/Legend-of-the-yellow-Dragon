package de.browsergame.model.entity.primaryKeys;

import de.browsergame.model.entity.Role;
import de.browsergame.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class UserRoleId implements Serializable {

    private Integer user;

    private Integer role;
}
