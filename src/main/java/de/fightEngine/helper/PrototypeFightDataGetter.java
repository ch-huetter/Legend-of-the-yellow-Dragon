package de.fightEngine.helper;

import de.game.model.entity.Monster;
import de.game.model.entity.PlayerCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrototypeFightDataGetter {

    public static PlayerCharacter getHero () {
        PlayerCharacter playerCharacter = new PlayerCharacter();
        playerCharacter.setVitality((short) 20);
        playerCharacter.setDexterity((short) 20);
        playerCharacter.setStrength((short) 40);
        playerCharacter.setAgility((short) 30);
        playerCharacter.setCurrentHealth(500);
        playerCharacter.setBaseHealth(500);
        playerCharacter.setMaxHealth(500);
        playerCharacter.setMaxStamina(200);
        playerCharacter.setCurrentStamina(200);
        playerCharacter.setBaseStamina(200);
        playerCharacter.setArmor(75);
        playerCharacter.setLevel((short) 10);
        playerCharacter.setName("HeroMan");
        playerCharacter.setActionsPerTurn((short) 3);
        return playerCharacter;
    }

    public static Monster getMonster1 () {
        Monster monster = new Monster();
        monster.setCurrentHealth(300);
        monster.setBaseHealth(300);
        monster.setMaxHealth(300);
        monster.setMaxStamina(115);
        monster.setCurrentStamina(115);
        monster.setBaseStamina(115);
        monster.setArmor(55);
        monster.setStrength((short) 18);
        monster.setAgility((short) 20);
        monster.setDexterity((short) 15);
        monster.setLevel((short) 8);
        monster.setName("Wolf 1");
        monster.setActionsPerTurn((short) 1);
        return monster;
    }

    public static Monster getMonster2 () {
        Monster monster = new Monster();
        monster.setCurrentHealth(315);
        monster.setBaseHealth(315);
        monster.setMaxHealth(315);
        monster.setMaxStamina(110);
        monster.setCurrentStamina(110);
        monster.setBaseStamina(110);
        monster.setArmor(57);
        monster.setStrength((short) 20);
        monster.setAgility((short) 24);
        monster.setDexterity((short) 15);
        monster.setLevel((short) 9);
        monster.setName("Wolf 2");
        monster.setActionsPerTurn((short) 2);

        return monster;
    }

    public static Monster getMonster3 () {
        Monster monster = new Monster();
        monster.setCurrentHealth(330);
        monster.setBaseHealth(330);
        monster.setMaxHealth(330);
        monster.setMaxStamina(100);
        monster.setCurrentStamina(100);
        monster.setBaseStamina(100);
        monster.setArmor(60);
        monster.setStrength((short) 16);
        monster.setDexterity((short) 15);
        monster.setAgility((short) 32);
        monster.setLevel((short) 11);
        monster.setName("Wolf 3");
        monster.setActionsPerTurn((short) 2);
        return monster;
    }


}
