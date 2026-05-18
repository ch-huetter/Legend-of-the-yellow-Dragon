package de.game.apiController.Sites.globalContext;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvable;
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
        List<MessageResolvable> mRRList = new ArrayList<>();
        mRRList.add(new MessageResolvable("logout.goodbye"));
        mRRList.add(new MessageResolvable("error.missingKey"));
        dto.setMessageResolvableList(mRRList);
    }


    @Override
    protected Class<GlobalContextApiDto> getDtoClass () {
        return GlobalContextApiDto.class;
    }
}
