package de.game.apiController.sites.dashboard;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvableImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class DashboardApiDtoFactory extends AbstractApiDtoFactory<DashboardApiDto> {

    @Override
    protected void addMessages (DashboardApiDto dto) {
        ArrayList<MessageResolvableImpl> mRL = new ArrayList<>();
        mRL.add(new MessageResolvableImpl("dashboard.description"));
        mRL.add(new MessageResolvableImpl("dashboard.description.noMessages"));
        mRL.add(new MessageResolvableImpl("button.seen"));
        dto.setMessageResolvableList(mRL);
    }

    @Override
    protected Class<DashboardApiDto> getDtoClass () {
        return DashboardApiDto.class;
    }
}
