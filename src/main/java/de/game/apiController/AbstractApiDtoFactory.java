package de.game.apiController;

import de.game.model.MessageResolvableImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public abstract class AbstractApiDtoFactory<T extends AbstractApiDto> {

    public T createDto () {
        T dto;
        try {
            dto = getDtoClass().getDeclaredConstructor().newInstance();
            addMessages((T) dto);
            return dto;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    protected void addMessages (T dto) {
        List<MessageResolvableImpl> messagesRessourceResolvableList = new ArrayList<>();
        dto.setMessageResolvableList(messagesRessourceResolvableList);
    }

    protected abstract Class<T> getDtoClass ();

}
