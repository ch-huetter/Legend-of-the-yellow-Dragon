package de.game.bean;

import org.springframework.stereotype.Service;

@Service
public class DefaultExperiencePerLevelGetter implements ExperiencePerLevelGetter {

    @Override
    public Integer getExperienceForLevel (Integer level) {
        return level * 1000;
    }
}
