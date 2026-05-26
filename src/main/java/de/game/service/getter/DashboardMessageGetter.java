package de.game.service.getter;

import de.game.model.entity.DashboardMessage;
import de.game.model.entity.User;
import de.game.model.repository.DashboardMessageRepository;
import de.game.service.UserService;
import de.game.util.basic.BasicEmptyCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardMessageGetter {

    private final DashboardMessageRepository dashboardMessageRepository;
    private final UserService userService;

    public List<DashboardMessage> getDashboardMessages (User user) {
        return fetchMessages(user);
    }

    public List<DashboardMessage> getDashboardMessages () {
        User user = userService.getLoggedInUserFromDb();
        return fetchMessages(user);
    }

    private List<DashboardMessage> fetchMessages (User user) {
        if (BasicEmptyCheck.isSet(user.getActivePlayerCharacterId())) {
            return getUserAndActivePlayerCharacterMessages(user);
        } else {
            return getUserMessages(user);
        }
    }

    private List<DashboardMessage> getUserMessages (User user) {
        return dashboardMessageRepository.getAllMessagesForUser(user.getId());
    }

    private List<DashboardMessage> getUserAndActivePlayerCharacterMessages (User user) {
        return dashboardMessageRepository.getAllMessagesForUserAndCharacter(user.getId(), user.getActivePlayerCharacterId());
    }
}
