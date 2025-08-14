package de.game.service.factory;

import de.game.model.entity.Role;
import de.game.util.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleFactory {

    public Role getRoleForEnumEntry (RoleEnum roleEnum) {
        return switch (roleEnum) {
            case PLAYER -> createPlayerRole();
            case GAMEMASTER -> createGamemasterRole();
            case ADMIN -> createAdminRole();
        };
    }

    public Role createAdminRole () {
        Role adminRole = new Role();
        adminRole.setId(RoleEnum.ADMIN.getId());
        adminRole.setName(RoleEnum.ADMIN.getName());
        adminRole.setDescription(RoleEnum.ADMIN.getDescription());
        return adminRole;
    }

    public Role createPlayerRole () {
        Role playerRole = new Role();
        playerRole.setId(RoleEnum.PLAYER.getId());
        playerRole.setName(RoleEnum.PLAYER.getName());
        playerRole.setDescription(RoleEnum.PLAYER.getDescription());
        return playerRole;
    }

    public Role createGamemasterRole () {
        Role gamemasterRole = new Role();
        gamemasterRole.setId(RoleEnum.GAMEMASTER.getId());
        gamemasterRole.setName(RoleEnum.GAMEMASTER.getName());
        gamemasterRole.setDescription(RoleEnum.GAMEMASTER.getDescription());
        return gamemasterRole;
    }
}
