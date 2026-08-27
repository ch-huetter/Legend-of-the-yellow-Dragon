package de.fightEngine.action;

import de.fightEngine.result.AbstractResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ActionResult extends AbstractResult {

}
