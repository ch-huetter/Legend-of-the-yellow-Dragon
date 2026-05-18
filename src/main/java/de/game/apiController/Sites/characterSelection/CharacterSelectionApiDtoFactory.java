package de.game.apiController.Sites.characterSelection;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvable;
import de.game.util.AddMessageBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CharacterSelectionApiDtoFactory extends AbstractApiDtoFactory<CharacterSelectionApiDto> {

    private final AddMessageBundleService addMessageBundleService;

    @Override
    protected void addMessages (CharacterSelectionApiDto dto) {
        ArrayList<MessageResolvable> messages = new ArrayList<>();
        messages.add(new MessageResolvable("characterSelection.card.button.select"));
        messages.add(new MessageResolvable("health"));
        messages.add(new MessageResolvable("experience"));
        messages.add(new MessageResolvable("gold"));
        messages.add(new MessageResolvable("class"));
        addMessageBundleService.addPlayerClassNames(messages);
        dto.setMessageResolvableList(messages);
    }

    @Override
    protected Class<CharacterSelectionApiDto> getDtoClass () {
        return CharacterSelectionApiDto.class;
    }


}
