package de.game.apiController.Sites.tavern;

import de.game.service.filler.dto.DtoFiller;
import org.springframework.stereotype.Service;

@Service
public class TavernApiDtoFiller implements DtoFiller<TavernApiDto> {

    @Override
    public void fillDto (TavernApiDto dto) {
        //Empty
    }

    @Override
    public Class<TavernApiDto> supports () {
        return TavernApiDto.class;
    }
}
