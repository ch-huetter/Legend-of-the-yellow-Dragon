package de.game.model;

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
public class MessageResolvable {

    /**
     * Resolvable MessageRessource Entry with a key and params to retrieve a Message.
     */
    public MessageResolvable (String key) {
        this.key = key;
    }

    public MessageResolvable (String key, String... params) {
        this.key = key;
        this.params = params;
    }

    String key;
    String[] params;
}
