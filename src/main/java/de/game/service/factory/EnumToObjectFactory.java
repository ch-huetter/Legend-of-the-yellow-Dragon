package de.game.service.factory;

import de.game.model.entity.Attribute;
import de.game.model.entity.Effect;
import de.game.model.entity.PlayerClass;
import de.game.model.entity.Setting;
import de.game.model.enums.AttributeEnum;
import de.game.model.enums.EffectEnum;
import de.game.model.enums.PlayerClassEnum;
import de.game.model.enums.SettingEnum;
import org.springframework.stereotype.Component;

@Component
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
        return PlayerClass.builder().id(playerClassEnum.getId()).name(playerClassEnum.getName()).effectsInitialized(false).build();
    }

    public Effect createEffect (EffectEnum effectEnum) {
        return Effect.builder().id(effectEnum.getId()).name(effectEnum.getName()).build();
    }

}
