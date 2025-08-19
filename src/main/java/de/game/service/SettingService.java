package de.game.service;

import de.game.model.entity.Setting;
import de.game.model.repository.SettingRepository;
import de.game.util.enums.SettingEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingRepository configurationRepository;

    @SuppressWarnings("unchecked")
    public <T> T getValue (SettingEnum config) {
        Optional<Setting> configuration = configurationRepository.findById(config.name());

        if (configuration.isEmpty())
            throw new NullPointerException("No Configuration Found for " + config.name());

        Object value = configuration.get().getValue();
        if (value == null)
            throw new NullPointerException("No Value for " + config.name());

        return (T) config.getCls().cast(value);
    }

}
