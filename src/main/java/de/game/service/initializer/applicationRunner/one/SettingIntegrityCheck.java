package de.game.service.initializer.applicationRunner.one;

import de.game.model.entity.Setting;
import de.game.model.enums.SettingEnum;
import de.game.model.repository.SettingRepository;
import de.game.service.factory.EnumToObjectFactory;
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

    private final EnumToObjectFactory enumToObjectFactory;
    private final SettingRepository settingRepository;

    @Override
    public void run (ApplicationArguments args) throws Exception {
        log.info("Starting Settings Integrity Check");
        int missingSettingAmount = 0;

        for (SettingEnum setting : SettingEnum.values()) {
            Optional<Setting> globalConfFromDb = settingRepository.findById(setting.name());
            if (globalConfFromDb.isEmpty()) {
                missingSettingAmount++;
                log.debug("Setting {} not found recreating with Default Value {}", setting.name(), setting.getDefaultValue());
                settingRepository.save(enumToObjectFactory.createSetting(setting));
            }
        }
        if (missingSettingAmount > 0) {
            log.info("Finished Integrity Check. Created {} missing Settings with default Values", missingSettingAmount);
        } else {
            log.info("Finished setting integrity check. No missing entries found");
        }

    }

}

