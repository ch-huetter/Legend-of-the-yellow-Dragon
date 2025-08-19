package de.game.service.factory;

import de.game.model.entity.Setting;
import de.game.util.enums.SettingEnum;
import org.springframework.stereotype.Service;

@Service
public class SettingFactory {

    public Setting createSetting (SettingEnum settingEnum) {
        Setting setting = new Setting();
        setting.setParameter(settingEnum.name());
        setting.setValue(settingEnum.getDefaultValue().toString());

        String settingComment = settingEnum.getComment();
        setting.setComment((settingComment != null && !settingComment.isEmpty()) ? settingComment : "");

        return setting;
    }

}
