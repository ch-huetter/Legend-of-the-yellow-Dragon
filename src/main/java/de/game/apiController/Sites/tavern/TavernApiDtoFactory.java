package de.game.apiController.Sites.tavern;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.bean.RestPriceGetter;
import de.game.model.MessageResolvableImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TavernApiDtoFactory extends AbstractApiDtoFactory<TavernApiDto> {

    private final RestPriceGetter restPriceGetter;

    @Override
    protected void addMessages (TavernApiDto dto) {
        List<MessageResolvableImpl> messagesRessourceResolvableList = new ArrayList<>();
        messagesRessourceResolvableList.add(new MessageResolvableImpl("tavern.description"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("tavern.restButton", String.valueOf(restPriceGetter.getRestPrice())));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("error.tavern.insufficientGold", String.valueOf(restPriceGetter.getRestPrice())));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("error.tavern.fullHealth"));
        dto.setMessageResolvableList(messagesRessourceResolvableList);
    }

    @Override
    protected Class<TavernApiDto> getDtoClass () {

        return TavernApiDto.class;
    }
}
