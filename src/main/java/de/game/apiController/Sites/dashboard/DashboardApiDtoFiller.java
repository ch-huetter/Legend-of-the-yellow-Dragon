package de.game.apiController.Sites.dashboard;

import de.game.model.entity.DashboardMessage;
import de.game.service.MessageResolvableResolver;
import de.game.service.filler.dto.DtoFiller;
import de.game.service.getter.DashboardMessageGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardApiDtoFiller implements DtoFiller<DashboardApiDto> {

    private final DashboardMessageGetter dashboardMessageGetter;
    private final MessageResolvableResolver messageResolvableResolver;

    @Override
    public void fillDto (DashboardApiDto dto) {
        List<DashboardMessage> dashboardMessageList = dashboardMessageGetter.getDashboardMessages();
        dto.setDashboardMessages(dashboardMessageList);
        messageResolvableResolver.resolveMessageResolvableAndAddToMap(dashboardMessageList, dto.getMessages());

    }

    @Override
    public Class<DashboardApiDto> supports () {
        return DashboardApiDto.class;
    }
}
