package de.fightEngine.effect;

import de.fightEngine.CombatantEntry;
import de.fightEngine.FightContext;
import de.fightEngine.effect.EffectEvokationContext.EffectEvocationContext;
import de.fightEngine.effect.EffectEvokationContext.PastEffectEvocationContext;
import de.fightEngine.effect.EffectEvokationContext.PreEffectEvocationContext;
import de.fightEngine.effect.implementation.Effect;
import de.fightEngine.io.IOManager;

import java.util.*;


public class EffectManager {

    private final Map<CombatantEntry, Map<EffectEvocationPoint, List<Effect>>> registeredEffectList;
    private final IOManager ioManager;
    private final FightContext fightContext;

    public EffectManager (List<CombatantEntry> combatantList, IOManager ioManager, FightContext fightContext) {
        registeredEffectList = new HashMap<>();
        this.ioManager = ioManager;
        this.fightContext = fightContext;

        for (CombatantEntry combatantEntry : combatantList) {
            Map<EffectEvocationPoint, List<Effect>> registeredEffects = new HashMap<>();

            for (EffectEvocationPoint effectEvocationPoint : Arrays.stream(EffectEvocationPoint.values()).toList()) {
                registeredEffects.put(effectEvocationPoint, new ArrayList<>());
            }

            registeredEffectList.put(combatantEntry, registeredEffects);
        }
    }

    /**
     * @param effectEvocationContext for the Effect Envocation Point that gets activated. Collects and dismisses effects when they reached their lifecycle
     */
    public void evokeEffects (EffectEvocationContext effectEvocationContext) {
        ArrayList<Effect> effectsToDismiss = new ArrayList<>();

        registeredEffectList.get(effectEvocationContext.getCombatantToEvokeEffects()).get(effectEvocationContext.getEvocationPoint())
                .forEach(effect -> {
                    applyEffect(effect.evokeEffect(effectEvocationContext), effectEvocationContext, effect);
                    if (effect.isDismissable()) {
                        effectsToDismiss.add(effect);
                    }
                });

        effectsToDismiss.forEach(this::removeRegisteredEffect);
    }

    private void applyEffect (EffectResult effectResult, EffectEvocationContext effectEvocationContext, Effect evokedEffect) {
        if (effectResult.equals(new EffectResult())) {
            return;
        }
        this.evokeEffects(new PreEffectEvocationContext(EffectEvocationPoint.PRE_EFFECT_EVOCATION, effectEvocationContext.getCombatantToEvokeEffects(), evokedEffect));

        fightContext.applyResult(effectResult);

        this.evokeEffects(new PastEffectEvocationContext(EffectEvocationPoint.PAST_EFFECT_EVOCATION, effectEvocationContext.getCombatantToEvokeEffects(), evokedEffect,
                                                         effectResult));
    }

    public void registerEffect (Effect effectToRegister) {
        Arrays.stream(effectToRegister.getEvocationPoints()).forEach(evocationPoint -> {
            registeredEffectList.get(effectToRegister.getTarget()).get(evocationPoint).add(effectToRegister);
        });
        System.out.println(effectToRegister.getRegistrationMsg());
    }

    public void removeRegisteredEffect (Effect effectToRemove) {
        effectToRemove.dismissEffect();
        Arrays.stream(effectToRemove.getEvocationPoints()).forEach(evocationPoint -> {
            registeredEffectList.get(effectToRemove.getTarget()).get(evocationPoint).remove(effectToRemove);

        });
    }

}
