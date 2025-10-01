package de.game.service;

import de.game.model.entity.Setting;
import de.game.model.enums.SettingEnum;
import de.game.model.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingRepository configurationRepository;

    public String getValue (SettingEnum config) {
        Optional<Setting> configuration = configurationRepository.findById(config.name());

        if (configuration.isEmpty())
            throw new NullPointerException("No Configuration Found for " + config.name());

        String value = configuration.get().getValue();
        if (value == null)
            throw new NullPointerException("No Value for " + config.name());

        return value;
    }

}
