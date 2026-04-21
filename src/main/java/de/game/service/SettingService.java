package de.game.service;

import de.game.model.entity.Setting;
import de.game.model.enums.SettingEnum;
import de.game.model.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class SettingService {

    private final SettingRepository configurationRepository;
    private final Map<String, String> settingCache = new ConcurrentHashMap<>();

    public String getValue (SettingEnum config) {
        settingCache.computeIfAbsent(config.name(), this::loadFromDB);
        return settingCache.get(config.name());
    }

    private String loadFromDB (String configName) {
        Optional<Setting> configuration = configurationRepository.findById(configName);

        if (configuration.isEmpty())
            throw new NullPointerException("No Configuration Found for " + configName);

        String value = configuration.get().getValue();
        if (value == null)
            throw new NullPointerException("No Value for " + configName);
        log.debug("Added {} to Cache", configName);
        return value;
    }

    public void clearCache () {
        settingCache.clear();
        log.info("Cleared Cache");
    }

}
