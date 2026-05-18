package de.game.service.filler.dto;

import de.game.apiController.AbstractApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DtoFillerService {

    private final DtoFillerSelector dtoFillerSelector;
    private final AbstractApiDtoFiller abstractApiDtoFiller;

    public <T> void fillDto (T dto) {

        if (dto instanceof AbstractApiDto) {
            abstractApiDtoFiller.fillDto((AbstractApiDto) dto);
        }

        dtoFillerSelector.getFiller(dto.getClass()).fillDto(dto);

    }
}
