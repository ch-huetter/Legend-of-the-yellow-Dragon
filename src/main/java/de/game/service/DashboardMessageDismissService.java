package de.game.service;

import de.game.bean.exception.MessageNotDismissableException;
import de.game.model.entity.DashboardMessage;
import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.model.entity.joinTable.PlayerCharacterDashboardMessage;
import de.game.model.entity.joinTable.UserDashboardMessage;
import de.game.model.repository.DashboardMessageRepository;
import de.game.model.repository.joinTableRepository.PlayerCharacterDashboardMessageRepository;
import de.game.model.repository.joinTableRepository.UserDashboardMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardMessageDismissService {

    private final UserService userService;
    private final DashboardMessageRepository dashboardMessageRepository;
    private final UserDashboardMessageRepository userDashboardMessageRepository;
    private final PlayerCharacterDashboardMessageRepository playerCharacterDashboardMessageRepository;

    public void dismissMessageForUser (String key) throws MessageNotDismissableException, NullPointerException {
        final DashboardMessage dashboardMessage = dashboardMessageRepository.findByMessageKey(key);
        final User             user             = userService.getLoggedInUserFromDb();

        if (dashboardMessage == null) {
            throw new NullPointerException("No DashboardMessage for Key : " + key);
        }
        if (!dashboardMessage.getDismissable()) {
            throw new MessageNotDismissableException();
        }

        if (dashboardMessage.getForUser()) {
            UserDashboardMessage userDashboardMessage = UserDashboardMessage.builder().user(user).dashboardMessage(dashboardMessage).dismissed(true).build();
            userDashboardMessageRepository.save(userDashboardMessage);
        }

        if (dashboardMessage.getForPlayerCharacter()) {
            PlayerCharacterDashboardMessage playerCharacterDashboardMessage =
                    PlayerCharacterDashboardMessage.builder().playerCharacter(new PlayerCharacter(user.getActivePlayerCharacterId())).dashboardMessage(dashboardMessage)
                            .dismissed(true).build();
            playerCharacterDashboardMessageRepository.save(playerCharacterDashboardMessage);
        }
    }
}
