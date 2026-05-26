package de.game.apiController.Sites.globalContext;

import de.game.util.basic.BasicEmptyCheck;
import groovy.lang.Singleton;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
@Singleton
public class GlobalContextMessageContext {

    private String messageHash;

    public String getMessageHash () {
        return BasicEmptyCheck.isSet(messageHash) ? messageHash : "";
    }


}
