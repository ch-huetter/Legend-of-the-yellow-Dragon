package de.game.apiController.Sites.globalContext;

import de.game.service.filler.dto.DtoFiller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GlobalContextApiDtoFiller implements DtoFiller<GlobalContextApiDto> {

    final GlobalContextMessageContext globalContextMessageContext;

    @Override
    public void fillDto (GlobalContextApiDto dto) {
        dto.setMessageHash(String.valueOf(dto.getMessages().hashCode()));
        globalContextMessageContext.setMessageHash(dto.getMessageHash());
    }

    @Override
    public Class<GlobalContextApiDto> supports () {
        return GlobalContextApiDto.class;
    }
}
