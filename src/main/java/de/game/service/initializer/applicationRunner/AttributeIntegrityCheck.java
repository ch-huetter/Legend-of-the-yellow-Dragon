package de.game.service.initializer.applicationRunner;

import de.game.model.entity.Attribute;
import de.game.model.repository.AttributeRepository;
import de.game.service.factory.AttributeFactory;
import de.game.util.enums.AttributeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1)
public class AttributeIntegrityCheck implements ApplicationRunner {

    private final AttributeRepository attributeRepository;
    private final AttributeFactory attributeFactory;

    @Override
    public void run (ApplicationArguments args) throws Exception {
        log.info("Running Attribute Integrity Check");

        for (AttributeEnum attribute : AttributeEnum.values()) {
            Attribute           attributeFromEnum = attributeFactory.createAttribute(attribute);
            Optional<Attribute> attrFromDatabase  = attributeRepository.findById(attribute.name());
            if (attrFromDatabase.isEmpty()) {
                log.info("Attribute {} missing in Database. Freshly Creating it", attribute.getName());
                attributeRepository.save(attributeFromEnum);
            }
        }
    }
}
