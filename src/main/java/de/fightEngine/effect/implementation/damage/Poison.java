package de.fightEngine.effect.implementation.damage;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import de.fightEngine.effect.EffectEvokationContext.EffectEvocationContext;
import de.fightEngine.effect.EffectEvokationContext.PastActionContext;
import de.fightEngine.effect.EffectResult;
import de.fightEngine.effect.implementation.Effect;
import de.fightEngine.effect.implementation.EffectKeyEnum;
import lombok.Getter;

@Getter
public class Poison extends Effect {

    private int poisonTier;
    private final int maxPoisonTier = 4;
    private final int[] damageBrackets = new int[]{35, 75, 120, 130};

    public Poison (int durationTurns, CombatantEntry source, CombatantEntry target, int poisonTier) {
        super(durationTurns, source, target, EffectKeyEnum.POISON);
        this.poisonTier = poisonTier;
    }

    @Override
    public EffectResult evokeEffect (EffectEvocationContext evocationContext) {
        return switch (evocationContext.getEvocationPoint()) {
            case PAST_TURN -> evokePastTurn((PastActionContext) evocationContext);
            default -> throw new IllegalArgumentException("Evocation Points is not supported");
        };
    }

    @Override
    public String getRegistrationMsg () {
        return source.getCombatant().getName() + " vergiftet " + target.getCombatant().getName() + " und fügt " + damageBrackets[poisonTier] + " Schaden jede Runde zu";
    }

    @Override
    public String getEvokationMsg (EffectEvocationPoint evocationPoint, EffectResult effectResult) {
        return switch (evocationPoint) {
            case PAST_TURN -> target.getCombatant().getName() + " bekommt " + effectResult.getMagicDamage() + " magischen Schaden";
            default -> null;
        };
    }

    @Override
    public EffectEvocationPoint[] getEvocationPoints () {
        return new EffectEvocationPoint[]{EffectEvocationPoint.PAST_TURN};
    }

    private EffectResult evokePastTurn (PastActionContext evocationContext) {
        if (poisonTier < 1 || poisonTier > 4) {
            throw new IllegalArgumentException("PoisonTier not within allowed Levels");
        }

        EffectResult result = new EffectResult();
        if (poisonTier < 4) {
            result.setMagicDamage(damageBrackets[poisonTier]);
            result.setBaseMagicDamage(damageBrackets[poisonTier]);
        } else {
            result.setTrueDamage(damageBrackets[poisonTier]);
            result.setBaseTrueDamage(damageBrackets[poisonTier]);
        }
        durationTurns--;
        return result;
    }
}
