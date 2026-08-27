package de.fightEngine;

import de.fightEngine.effect.EffectManager;
import de.fightEngine.effect.implementation.Effect;
import de.fightEngine.result.AbstractResult;
import de.fightEngine.result.ResultApplicator;
import de.fightEngine.turnOrder.TurnOrderManager;

import java.util.List;


public class FightContext {
    private boolean initialized = false;
    private Fight fight;
    private EffectManager effectManager;
    private CombatantManager combatantManager;
    private ResultApplicator resultApplicator;
    private TurnOrderManager turnOrderManager;

    public void initializeFightContext (Fight fight, EffectManager effectManager, CombatantManager combatantManager, ResultApplicator resultApplicator,
                                        TurnOrderManager turnOrderManager) {
        if (initialized) {
            throw new IllegalStateException("FightContext is already initialized");
        }
        this.fight = fight;
        this.effectManager = effectManager;
        this.combatantManager = combatantManager;
        this.resultApplicator = resultApplicator;
        this.turnOrderManager = turnOrderManager;
        initialized = true;
    }

    public void registerEffect (Effect effect) {
        isInitialized();

        effectManager.registerEffect(effect);
    }

    public void combatantIsDead () {
        isInitialized();
        combatantManager.combatantIsDead();
    }

    public void applyResult (AbstractResult result) {
        isInitialized();
        resultApplicator.applyResult(result);
    }

    private void isInitialized () {
        if (!initialized)
            throw new IllegalStateException("FightContext used before initialization");
    }

    /**
     * Gets an actual List of Combatants. To keep an actual List of Combatants at all times you can subscribe to onCombatantListChangeEvent!
     *
     * @return the CombatantList
     */
    public List<CombatantEntry> getCombatantList () {
        isInitialized();
        return combatantManager.getCombatantList();
    }

    public CombatantEntry getCurrentCombatant () {
        isInitialized();
        return turnOrderManager.getCurrentCombatant();
    }

}
