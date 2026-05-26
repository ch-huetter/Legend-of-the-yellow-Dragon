package de.game.apiController.Sites.globalContext;

import de.game.service.filler.dto.DtoFillerService;
import de.game.util.basic.BasicEmptyCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/game/globalContext")
@RequiredArgsConstructor
@Slf4j
public class GlobalContextApiController {

    final GlobalContextApiDtoFactory globalContextApiDtoFactory;
    final DtoFillerService dtoFillerService;
    final GlobalContextMessageContext globalContextMessageContext;
    final BasicEmptyCheck basicEmptyCheck;

    @GetMapping("/getGlobalContext")
    public ResponseEntity<GlobalContextApiDto> initGlobalContext () {
        log.info("Sending globalContext");
        GlobalContextApiDto dto = globalContextApiDtoFactory.createDto();
        dtoFillerService.fillDto(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/checkGlobalContext")
    public ResponseEntity<GlobalContextApiDto> checkGlobalContext (@RequestBody checkGlobalContextRequest checkGlobalContextRequest) {
        GlobalContextApiDto dto;
        if (globalContextMessageContext.getMessageHash().equals(checkGlobalContextRequest.messageHash)) {
            dto = globalContextApiDtoFactory.createOkDto();
        } else {
            dto = globalContextApiDtoFactory.createDto();
            dtoFillerService.fillDto(dto);
            dto.setStatus(GlobalContextStatus.OUTDATED.name());
        }
        return ResponseEntity.ok(dto);
    }

    public record checkGlobalContextRequest(String messageHash) {
    }
}
