package de.game.service.factory;

import de.game.model.entity.PlayerClass;
import de.game.model.enums.PlayerClassEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayerClassFactory {

    private final EnumToObjectFactory enumToObjectFactory;

    public List<PlayerClass> createPlayerClassesList () {
        ArrayList<PlayerClass> playerClasses = new ArrayList<>();

        for (PlayerClassEnum playerClassEnum : PlayerClassEnum.values()) {
            PlayerClass playerClass = enumToObjectFactory.createPlayerClass(playerClassEnum);
            playerClasses.add(playerClass);
        }
        return playerClasses;
    }

}
