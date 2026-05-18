package de.game.apiController.Sites.tavern;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.bean.RestPriceGetter;
import de.game.model.MessageResolvable;
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
        List<MessageResolvable> messagesRessourceResolvableList = new ArrayList<>();
        messagesRessourceResolvableList.add(new MessageResolvable("tavern.description"));
        messagesRessourceResolvableList.add(new MessageResolvable("tavern.restButton", String.valueOf(restPriceGetter.getRestPrice())));
        messagesRessourceResolvableList.add(new MessageResolvable("error.tavern.insufficientGold", String.valueOf(restPriceGetter.getRestPrice())));
        messagesRessourceResolvableList.add(new MessageResolvable("error.tavern.fullHealth"));
        dto.setMessageResolvableList(messagesRessourceResolvableList);
    }

    @Override
    protected Class<TavernApiDto> getDtoClass () {

        return TavernApiDto.class;
    }
}
