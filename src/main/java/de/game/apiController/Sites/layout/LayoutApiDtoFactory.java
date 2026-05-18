package de.game.apiController.Sites.layout;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class LayoutApiDtoFactory extends AbstractApiDtoFactory<LayoutApiDto> {

    protected void addMessages (LayoutApiDto dto) {
        ArrayList<MessageResolvable> messages = new ArrayList<>();
        messages.add(new MessageResolvable("health"));
        messages.add(new MessageResolvable("gold"));
        messages.add(new MessageResolvable("experience"));
        dto.setMessageResolvableList(messages);
    }

    @Override
    protected Class<LayoutApiDto> getDtoClass () {
        return LayoutApiDto.class;
    }
}
