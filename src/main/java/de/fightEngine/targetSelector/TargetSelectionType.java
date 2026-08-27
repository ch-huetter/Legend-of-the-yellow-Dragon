package de.fightEngine.targetSelector;

public enum TargetSelectionType {

    MULTI_TARGET_EXCLUSIVE,//Select X Targets. A single Combatant can only be targeted once
    MULTI_TARGET_INCLUSIVE,//Select X Targets. A single Combatant can be targeted multiple Times
    AREA_OF_EFFECT // Select One Target. Adjacent Combatants will be targeted as well
}
