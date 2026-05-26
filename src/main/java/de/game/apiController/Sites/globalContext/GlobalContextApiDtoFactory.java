package de.game.apiController.Sites.globalContext;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvableImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalContextApiDtoFactory extends AbstractApiDtoFactory<GlobalContextApiDto> {

    public GlobalContextApiDto createOkDto () {
        GlobalContextApiDto globalContextApiDto = new GlobalContextApiDto();
        globalContextApiDto.setStatus(GlobalContextStatus.OK.name());
        return globalContextApiDto;
    }

    @Override
    protected void addMessages (GlobalContextApiDto dto) {
        List<MessageResolvableImpl> mRRList = new ArrayList<>();
        mRRList.add(new MessageResolvableImpl("logout.goodbye"));
        mRRList.add(new MessageResolvableImpl("error.missingKey"));
        dto.setMessageResolvableList(mRRList);
    }


    @Override
    protected Class<GlobalContextApiDto> getDtoClass () {
        return GlobalContextApiDto.class;
    }
}
