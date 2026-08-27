package de.game.apiController.sites.globalContext;

import de.game.apiController.AbstractApiDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class GlobalContextApiDto extends AbstractApiDto {
    String status;
    String messageHash;
}
