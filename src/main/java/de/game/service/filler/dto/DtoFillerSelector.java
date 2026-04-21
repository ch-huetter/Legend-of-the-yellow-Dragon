package de.game.service.filler.dto;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
/**
 * Used to find the specifig filler for a specific Dto! Never use directly! Always use CharacterDtoFillerService instead!
 */
public class DtoFillerSelector {

    private final HashMap<Class<?>, DtoFiller<?>> dtoFillerMap = new HashMap<>();

    public DtoFillerSelector (List<DtoFiller<?>> fillerList) {
        for (DtoFiller<?> filler : fillerList) {
            if (dtoFillerMap.containsKey(filler.supports())) {
                throw new IllegalArgumentException("Filler for Class " + filler.supports() + " already exists");
            } else {
                dtoFillerMap.put(filler.supports(), filler);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> DtoFiller<T> getFiller (Class<?> clazz) {
        return (DtoFiller<T>) dtoFillerMap.get(clazz);
    }

}
