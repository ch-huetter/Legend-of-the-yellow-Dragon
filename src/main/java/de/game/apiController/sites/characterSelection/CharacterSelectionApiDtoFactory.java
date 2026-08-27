package de.game.apiController.sites.characterSelection;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvableImpl;
import de.game.util.messageBundle.AddMessageBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CharacterSelectionApiDtoFactory extends AbstractApiDtoFactory<CharacterSelectionApiDto> {

    private final AddMessageBundleService addMessageBundleService;

    @Override
    protected void addMessages (CharacterSelectionApiDto dto) {
        ArrayList<MessageResolvableImpl> messages = new ArrayList<>();
        messages.add(new MessageResolvableImpl("characterSelection.card.button.select"));
        messages.add(new MessageResolvableImpl("health"));
        messages.add(new MessageResolvableImpl("experience"));
        messages.add(new MessageResolvableImpl("gold"));
        messages.add(new MessageResolvableImpl("class"));
        addMessageBundleService.addPlayerClasses(messages);
        dto.setMessageResolvableList(messages);
    }

    @Override
    protected Class<CharacterSelectionApiDto> getDtoClass () {
        return CharacterSelectionApiDto.class;
    }


}
