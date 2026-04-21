package de.game.service.filler.dto;

import de.game.controller.dto.AbstractApiDto;
import de.game.util.basic.BasicStringCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Filler for Base Level Dtos. This needs to be called when an AbstractApiDto is used.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class AbstractApiDtoFiller {

    private final MessageSource messageSource;

    public void fillDto (AbstractApiDto abstractApiDto) {
        Map<String, String> messages = abstractApiDto.getMessages();
        messages.replaceAll((k, v) -> {
            return BasicStringCheck.isSet(k) ? messageSource.getMessage(k, null, LocaleContextHolder.getLocale()) : "";
        });

    }
}
