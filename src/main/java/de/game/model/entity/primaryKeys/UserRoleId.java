package de.game.model.entity.primaryKeys;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class UserRoleId implements Serializable {

    private Integer user;

    private Integer role;
}
