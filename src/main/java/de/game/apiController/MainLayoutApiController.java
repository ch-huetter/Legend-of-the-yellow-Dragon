package de.game.apiController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class MainLayoutApiController {

    @GetMapping("/mainInit")
    public ResponseEntity<String> initLayout () {
        return ResponseEntity.ok().build();
    }

}
