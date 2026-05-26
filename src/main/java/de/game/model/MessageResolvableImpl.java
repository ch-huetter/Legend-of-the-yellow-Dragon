package de.game.model;

import de.game.bean.MessageResolvable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
public class MessageResolvableImpl implements MessageResolvable {

    /**
     * Resolvable MessageRessource Entry with a key and params to retrieve a Message.
     */
    public MessageResolvableImpl (String key) {
        this.key = key;
    }

    public MessageResolvableImpl (String key, String... params) {
        this.key = key;
        this.params = params;
    }

    String key;
    String[] params;
}
