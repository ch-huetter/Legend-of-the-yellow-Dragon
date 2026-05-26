package de.game.apiController.Sites.layout;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvableImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class LayoutApiDtoFactory extends AbstractApiDtoFactory<LayoutApiDto> {

    protected void addMessages (LayoutApiDto dto) {
        ArrayList<MessageResolvableImpl> messages = new ArrayList<>();
        messages.add(new MessageResolvableImpl("health"));
        messages.add(new MessageResolvableImpl("gold"));
        messages.add(new MessageResolvableImpl("experience"));
        dto.setMessageResolvableList(messages);
    }

    @Override
    protected Class<LayoutApiDto> getDtoClass () {
        return LayoutApiDto.class;
    }
}
