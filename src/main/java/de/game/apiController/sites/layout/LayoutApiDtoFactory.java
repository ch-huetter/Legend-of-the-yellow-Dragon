package de.game.apiController.sites.layout;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvableImpl;
import de.game.util.messageBundle.AddMessageBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class LayoutApiDtoFactory extends AbstractApiDtoFactory<LayoutApiDto> {

    private final AddMessageBundleService addMessageBundleService;

    protected void addMessages (LayoutApiDto dto) {
        ArrayList<MessageResolvableImpl> messages = new ArrayList<>();
        messages.add(new MessageResolvableImpl("health"));
        messages.add(new MessageResolvableImpl("gold"));
        messages.add(new MessageResolvableImpl("experience"));

        addMessageBundleService.addPlayerClasses(messages);
        dto.setMessageResolvableList(messages);
    }

    @Override
    protected Class<LayoutApiDto> getDtoClass () {
        return LayoutApiDto.class;
    }
}
