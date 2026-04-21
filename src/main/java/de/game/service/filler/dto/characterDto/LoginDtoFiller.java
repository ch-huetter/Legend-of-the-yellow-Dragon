package de.game.service.filler.dto.characterDto;

import de.game.controller.dto.LoginDto;
import de.game.service.filler.dto.DtoFiller;
import org.springframework.stereotype.Service;

@Service
public class LoginDtoFiller implements DtoFiller<LoginDto> {
    /**
     * @param dto
     */
    @Override
    public void fillDto (LoginDto dto) {
        //Empty
    }

    /**
     * @return Class
     */
    @Override
    public Class<LoginDto> supports () {
        return LoginDto.class;
    }
}
