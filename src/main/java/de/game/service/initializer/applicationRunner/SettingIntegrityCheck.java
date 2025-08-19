package de.game.service.initializer.applicationRunner;

import de.game.model.entity.Setting;
import de.game.model.repository.SettingRepository;
import de.game.service.factory.SettingFactory;
import de.game.util.enums.SettingEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(1)
public class SettingIntegrityCheck implements ApplicationRunner {

    private final SettingFactory settingFactory;
    private final SettingRepository settingRepository;

    @Override
    public void run (ApplicationArguments args) throws Exception {
        log.info("Starting Settings Integrity Check");
        int missingSettingNumber = 0;
        for (SettingEnum setting : SettingEnum.values()) {
            Optional<Setting> globalConfFromDb = settingRepository.findById(setting.name());
            if (globalConfFromDb.isEmpty()) {
                missingSettingNumber++;
                log.debug("Setting {} not found recreating with Default Value {}", setting.name(), setting.getDefaultValue());
                settingRepository.save(settingFactory.createSetting(setting));
            }
        }
        log.info("Finished Integrity Check. Created {} missing Settings with default Values", missingSettingNumber);
    }
}
