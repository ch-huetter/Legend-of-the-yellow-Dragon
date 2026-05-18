package de.game.service.filler.dto;

import de.game.apiController.AbstractApiDto;
import de.game.model.MessageResolvable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filler for Base Level Dto`s. This needs to be called when an AbstractApiDto is used.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class AbstractApiDtoFiller {

    private final MessageSource messageSource;

    public void fillDto (AbstractApiDto abstractApiDto) {
        Map<String, String>     messages              = new HashMap<>();
        List<MessageResolvable> messageResolvableList = abstractApiDto.getMessageResolvableList();

        messageResolvableList.forEach((msgResolvable) -> messages.put(msgResolvable.getKey(), messageSource.getMessage(msgResolvable.getKey(), msgResolvable.getParams(),
                                                                                                                       LocaleContextHolder.getLocale())));
        abstractApiDto.setMessageResolvableList(null);
        abstractApiDto.setMessages(messages);
    }
}
