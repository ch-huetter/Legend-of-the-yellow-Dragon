package de.game.util.attribute.valueCalculator;

import de.game.model.entity.LivingEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Builds a Map of all available calculators
 */
@Service
@RequiredArgsConstructor
public class ValueCalculatorGetter {

    private final Map<Class<? extends LivingEntity>, AttributeValueCalculator<?>> calculatorMap = new HashMap<>();

    private final CharacterValueCalculator characterValueCalculator;
    private final MonsterValueCalculator monsterValueCalculator;

    @PostConstruct
    private void init () {
        calculatorMap.put(characterValueCalculator.getSupportedClass(), characterValueCalculator);
        calculatorMap.put(monsterValueCalculator.getSupportedClass(), monsterValueCalculator);
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity> AttributeValueCalculator<T> getValueCalculator (Class<T> clazz) {
        return (AttributeValueCalculator<T>) calculatorMap.get(clazz);
    }

}
