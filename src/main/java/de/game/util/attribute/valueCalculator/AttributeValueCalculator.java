package de.game.util.attribute.valueCalculator;

import de.game.model.entity.LivingEntity;

public interface AttributeValueCalculator<T extends LivingEntity> {
    public void calculateEntityValues (T entity);
    
    public Class<T> getSupportedClass ();
}
