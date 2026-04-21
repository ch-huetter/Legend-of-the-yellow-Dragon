package de.game.service;

import de.game.model.entity.LivingEntity;
import de.game.util.attribute.valueCalculator.ValueCalculatorGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateCharacterValuesService {

    private final ValueCalculatorGetter calculatorGetter;

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity> void calculateCharacterValues (T entity) {
        calculatorGetter.getValueCalculator((Class<T>) entity.getClass()).calculateEntityValues(entity);

    }
}
