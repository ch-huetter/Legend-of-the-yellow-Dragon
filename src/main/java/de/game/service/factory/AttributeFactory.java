package de.game.service.factory;

import de.game.model.entity.Attribute;
import de.game.util.enums.AttributeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttributeFactory {

    public Attribute createAttribute (AttributeEnum attrEnum) {
        Attribute attr = new Attribute();
        attr.setKey(attrEnum.name());
        attr.setName(attrEnum.getName());
        return attr;
    }

}
