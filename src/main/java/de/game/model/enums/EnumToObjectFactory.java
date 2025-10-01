package de.game.model.enums;

import de.game.model.entity.Attribute;
import de.game.model.entity.PlayerClass;
import de.game.model.entity.Setting;

public class EnumToObjectFactory {

    public Attribute createAttribute (AttributeEnum attrEnum) {
        Attribute attr = new Attribute();
        attr.setKey(attrEnum.getKey());
        return attr;
    }

    public Setting createSetting (SettingEnum settingEnum) {
        Setting setting = new Setting();
        setting.setParameter(settingEnum.name());
        setting.setValue(settingEnum.getDefaultValue());

        String settingComment = settingEnum.getComment();
        setting.setComment((settingComment != null && !settingComment.isEmpty()) ? settingComment : "");

        return setting;
    }

    public PlayerClass createPlayerClass (PlayerClassEnum playerClassEnum) {
        return PlayerClass.builder().id(playerClassEnum.getId()).name(playerClassEnum.getName()).build();
    }

}
