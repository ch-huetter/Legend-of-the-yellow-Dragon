package de.game.apiController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.game.model.MessageResolvable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Base Dto for all Game sites. Only extend in Dtos that are used for React Uis
 */
@Component
@Getter
@Setter
public class AbstractApiDto {
    //TODO Navigation einträge müssen hier auch gefüllt werden! Dafür muss aber erstmal die Datenbank gestützte Navigation konfiguriert werden!

    private Map<String, String> messages;
    @JsonIgnore
    private List<MessageResolvable> messageResolvableList;
    private String backgroundUrl;

}
