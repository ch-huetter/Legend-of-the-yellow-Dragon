package de.fightEngine.effect;

import de.fightEngine.CombatantEntry;
import de.fightEngine.FightContext;
import de.fightEngine.effect.EffectEvokationContext.EffectEvocationContext;
import de.fightEngine.effect.EffectEvokationContext.PastEffectEvocationContext;
import de.fightEngine.effect.EffectEvokationContext.PreEffectEvocationContext;
import de.fightEngine.effect.implementation.Effect;
import de.fightEngine.io.IOManager;
import de.fightEngine.io.IOPrinter;

import java.util.*;


public class EffectManager {

    private final Map<CombatantEntry, Map<EffectEvocationPoint, List<Effect>>> registeredEffectList;
    private final IOPrinter ioPrinter;
    private final FightContext fightContext;

    public EffectManager (List<CombatantEntry> combatantList, IOManager ioPrinter, FightContext fightContext) {
        registeredEffectList = new HashMap<>();
        this.ioPrinter = ioPrinter.getIOPrinterInstance();
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
        ioPrinter.printDebugMsg("Evoking Effect evocation Point " + effectEvocationContext.getEvocationPoint() + " for " +
                                effectEvocationContext.getCombatantToEvokeEffects().getCombatant().getName());
        registeredEffectList.get(effectEvocationContext.getCombatantToEvokeEffects()).get(effectEvocationContext.getEvocationPoint())
                .forEach(effect -> {
                    ioPrinter.printTraceMsg("Evoking Effect " + effect.getEffectKeyEnum());
                    EffectResult effectResult = effect.evokeEffect(effectEvocationContext);
                    if (effectResult != null) {
                        ioPrinter.printTraceMsg("Effect produced a result. Applying it");
                        applyEffect(effectResult, effectEvocationContext, effect);
                    }
                    if (effect.isDismissable()) {
                        ioPrinter.printTraceMsg("Effect is Dismissable. Adding it to dismissable list");
                        effectsToDismiss.add(effect);
                    }
                });

        effectsToDismiss.forEach(this::removeRegisteredEffect);
    }

    private void applyEffect (EffectResult effectResult, EffectEvocationContext effectEvocationContext, Effect evokedEffect) {

        this.evokeEffects(new PreEffectEvocationContext(EffectEvocationPoint.PRE_EFFECT_EVOCATION, effectEvocationContext.getCombatantToEvokeEffects(), evokedEffect));

        fightContext.applyResult(effectResult);

        this.evokeEffects(new PastEffectEvocationContext(EffectEvocationPoint.PAST_EFFECT_EVOCATION, effectEvocationContext.getCombatantToEvokeEffects(), evokedEffect,
                                                         effectResult));
    }

    public void registerEffect (Effect effectToRegister) {
        ioPrinter.printDebugMsg("Registering Effect " + effectToRegister.getEffectKeyEnum().getName() + " to " + effectToRegister.getEvocationPoints().length + " Evocation " +
                                "Points");
        Arrays.stream(effectToRegister.getEvocationPoints()).forEach(evocationPoint -> {
            ioPrinter.printTraceMsg("Registering Effect " + effectToRegister.getEffectKeyEnum() + " to EvocationPoint " + evocationPoint.toString());
            registeredEffectList.get(effectToRegister.getTarget()).get(evocationPoint).add(effectToRegister);
        });
        ioPrinter.printMsg(effectToRegister.getRegistrationMsg());
    }

    public void removeRegisteredEffect (Effect effectToRemove) {
        effectToRemove.dismissEffect();
        Arrays.stream(effectToRemove.getEvocationPoints()).forEach(evocationPoint -> {
            registeredEffectList.get(effectToRemove.getTarget()).get(evocationPoint).remove(effectToRemove);

        });
    }

}
