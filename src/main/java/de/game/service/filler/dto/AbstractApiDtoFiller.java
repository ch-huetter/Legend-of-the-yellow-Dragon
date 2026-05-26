package de.game.service.filler.dto;

import de.game.apiController.AbstractApiDto;
import de.game.service.MessageResolvableResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Filler for Base Level Dto`s. This needs to be called when an AbstractApiDto is used.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class AbstractApiDtoFiller {

    private final MessageResolvableResolver messageResolvableResolver;

    public void fillDto (AbstractApiDto abstractApiDto) {
        abstractApiDto.setMessages(messageResolvableResolver.resolveMessages(abstractApiDto.getMessageResolvableList()));
    }
}
