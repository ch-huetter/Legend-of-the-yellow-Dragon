package de.game.model.repository.joinTableRepository;

import de.game.model.entity.joinTable.PlayerCharacterDashboardMessage;
import de.game.model.entity.primaryKeys.PlayerCharacterDashboardMessageId;
import org.springframework.data.repository.CrudRepository;

public interface PlayerCharacterDashboardMessageRepository extends CrudRepository<PlayerCharacterDashboardMessage, PlayerCharacterDashboardMessageId> {
}
