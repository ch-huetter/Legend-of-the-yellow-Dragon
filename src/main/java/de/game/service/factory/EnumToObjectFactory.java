package de.game.service.factory;

import de.game.model.entity.Setting;
import de.game.model.enums.SettingEnum;
import org.springframework.stereotype.Component;

@Component
public class EnumToObjectFactory {

    public Setting createSetting (SettingEnum settingEnum) {
        Setting setting = new Setting();
        setting.setParameter(settingEnum.name());
        setting.setValue(settingEnum.getDefaultValue());

        String settingComment = settingEnum.getComment();
        setting.setComment((settingComment != null && !settingComment.isEmpty()) ? settingComment : "");

        return setting;
    }

}
