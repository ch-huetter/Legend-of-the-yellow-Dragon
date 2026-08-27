package de.game.apiController.sites.dashboard;

import de.game.apiController.AbstractApiDto;
import de.game.model.entity.DashboardMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Getter
@Setter
public class DashboardApiDto extends AbstractApiDto {
    private List<DashboardMessage> dashboardMessages;

}
