package de.game.service.initializer.applicationRunner.one;

import de.game.model.entity.Effect;
import de.game.model.enums.EffectEnum;
import de.game.model.repository.EffectRepository;
import de.game.service.factory.EnumToObjectFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(1)
public class EffectsIntegrityCheck implements ApplicationRunner {

    private final EffectRepository effectRepository;
    private final EnumToObjectFactory enumToObjectFactory;

    @Override
    public void run (ApplicationArguments args) throws Exception {
        log.info("Starting effect integrity check");
        int minssingEntriesAmount = 0;

        for (EffectEnum effect : EffectEnum.values()) {
            Optional<Effect> effectFromDatabase = effectRepository.findByName(effect.getName());
            if (effectFromDatabase.isEmpty()) {
                minssingEntriesAmount++;
                log.info("Effect {} not found recreating it", effect.getName());
                effectRepository.save(enumToObjectFactory.createEffect(effect));
            }
        }
        if (minssingEntriesAmount > 0) {
            log.info("Finished effect integrity check. Created {} missing effects", minssingEntriesAmount);
        } else {
            log.info("Finished effect integrity check. No missing effects found");
        }
    }
}
