package de.game.service;

import de.game.bean.MessageResolvable;
import de.game.util.basic.BasicEmptyCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageResolvableResolver {

    private final MessageSource messageSource;
    private final Map<String, String> missingErrorMsg;

    /**
     * Resolves a List of messageResolvable and adds them to the given Map
     */
    public void resolveMessageResolvableAndAddToMap (List<? extends MessageResolvable> messageResolvableList, Map<String, String> messages) {
        for (MessageResolvable resolvable : messageResolvableList) {
            messages.put(resolvable.getKey(), resolveMessage(resolvable));
        }
    }

    /**
     * @return a Map of message_keys and resolved messages
     */
    public Map<String, String> resolveMessages (List<? extends MessageResolvable> messageResolvableList) {
        HashMap<String, String> messages = new HashMap<>();
        for (MessageResolvable resolvable : messageResolvableList) {
            messages.put(resolvable.getKey(), resolveMessage(resolvable));
        }
        return messages;
    }

    /**
     * @return the resolvedMessage this messageResolvable is describing
     */
    public String resolveMessage (MessageResolvable messageResolvable) {
        String message = messageSource.getMessage(messageResolvable.getKey(), messageResolvable.getParams(), null,
                                                  LocaleContextHolder.getLocale());
        if (!BasicEmptyCheck.isSet(message)) {
            log.error("No Message for Key : {}", messageResolvable.getKey());
            message = getMissingErrorMsg(messageResolvable.getKey());
        }

        return message;
    }

    private String getMissingErrorMsg (String key) {
        Locale locale = LocaleContextHolder.getLocale();
        if (!missingErrorMsg.containsKey(LocaleContextHolder.getLocale().toLanguageTag())) {
            missingErrorMsg.put(locale.toLanguageTag(), messageSource.getMessage("error.missingKey", new String[]{key}, locale));
        }
        return missingErrorMsg.get(LocaleContextHolder.getLocale().toLanguageTag());
    }

}
