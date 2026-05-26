package de.game.model.repository;

import de.game.model.entity.DashboardMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardMessageRepository extends CrudRepository<DashboardMessage, String> {

    @Query("SELECT dm FROM DashboardMessage as dm " +
           "LEFT JOIN UserDashboardMessage as udm ON dm.messageKey =  udm.dashboardMessage.messageKey AND udm.user.id = ?1 " +
           "WHERE (udm.user.id = ?1 AND dm.forUser AND udm.dismissed = false OR udm.user.id IS NULL) " +
           "ORDER BY dm.priority DESC")
    public List<DashboardMessage> getAllMessagesForUser (Integer userId);


    @Query("SELECT dm FROM DashboardMessage as dm " +
           "LEFT JOIN UserDashboardMessage as udm ON dm.messageKey =  udm.dashboardMessage.messageKey AND udm.user.id = ?1 " +
           "LEFT JOIN PlayerCharacterDashboardMessage as pcdm ON dm.messageKey = pcdm.dashboardMessage.messageKey AND pcdm.playerCharacter.id = ?2 " +
           "WHERE (dm.forUser = true AND (udm.user.id IS NULL OR udm.dismissed = false)) OR (dm.forPlayerCharacter = true AND " +
           "(pcdm.dismissed OR pcdm.playerCharacter IS NULL))" +
           "ORDER BY dm.priority DESC")
    public List<DashboardMessage> getAllMessagesForUserAndCharacter (Integer userId, Integer CharacterId);

    public DashboardMessage findByMessageKey (String key);

}
