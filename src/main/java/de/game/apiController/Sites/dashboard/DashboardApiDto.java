package de.game.apiController.Sites.dashboard;

import de.game.apiController.AbstractApiDto;
import de.game.model.entity.DashboardMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
@Getter
@Setter
public class DashboardApiDto extends AbstractApiDto {
    private List<DashboardMessage> dashboardMessages;

}
