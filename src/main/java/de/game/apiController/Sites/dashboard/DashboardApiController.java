package de.game.apiController.Sites.dashboard;

import de.game.bean.exception.MessageNotDismissableException;
import de.game.model.entity.DashboardMessage;
import de.game.service.DashboardMessageDismissService;
import de.game.service.filler.dto.DtoFillerService;
import de.game.service.getter.DashboardMessageGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/dashboard")
@Slf4j
public class DashboardApiController {

    private final DashboardApiDtoFactory dashboardApiDtoFactory;
    private final DtoFillerService dtoFillerService;
    private final DashboardMessageGetter dashboardMessageGetter;
    private final DashboardMessageDismissService dashboardMessageDismissService;
    private final MessageSource messageSource;

    @GetMapping("/init")
    public ResponseEntity<DashboardApiDto> initDashboard () {
        log.info("dashboard init called");
        DashboardApiDto dashboardApiDto = dashboardApiDtoFactory.createDto();
        dtoFillerService.fillDto(dashboardApiDto);
        return ResponseEntity.ok(dashboardApiDto);
    }

    @PostMapping("/dismiss")
    public ResponseEntity<List<DashboardMessage>> dismissMessage (@RequestBody DismissMessageRequest dismissMessageRequest) {
        String messageKey = dismissMessageRequest.key;
        try {
            dashboardMessageDismissService.dismissMessageForUser(messageKey);
            List<DashboardMessage> dashboardMessages = dashboardMessageGetter.getDashboardMessages();
            return ResponseEntity.ok(dashboardMessages);
        } catch (MessageNotDismissableException e) {
            log.error(messageSource.getMessage("error.notDismissable", new String[]{messageKey}, LocaleContextHolder.getLocale()), e);
        } catch (NullPointerException e) {
            log.error(messageSource.getMessage("error.missingKey", new String[]{messageKey}, LocaleContextHolder.getLocale()), e);
        }
        return ResponseEntity.unprocessableEntity().body(null);
    }

    public record DismissMessageRequest(String key) {
    }

    ;

}
