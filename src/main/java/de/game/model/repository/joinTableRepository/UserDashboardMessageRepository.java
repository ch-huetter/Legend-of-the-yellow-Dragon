package de.game.model.repository.joinTableRepository;

import de.game.model.entity.joinTable.UserDashboardMessage;
import de.game.model.entity.primaryKeys.UserDashboardMessageId;
import org.springframework.data.repository.CrudRepository;

public interface UserDashboardMessageRepository extends CrudRepository<UserDashboardMessage, UserDashboardMessageId> {
}
