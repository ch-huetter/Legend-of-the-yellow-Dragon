package de.game.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Base Dto for all Game sites. Only extend in Dtos that are used for React Uis
 */
@Component
@Getter
@Setter
public abstract class AbstractApiDto {
    //TODO Navigation einträge müssen hier auch gefüllt werden! Dafür muss aber erstmal die Datenbank gestützte Navigation konfiguriert werden!

    private Map<String, String> messages;
    private String backgroundUrl;

}
