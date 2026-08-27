package de.fightEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventBus {

    private final List<Runnable> onTurnEndListener;
    private final List<Runnable> onRoundEndListener;
    private final List<Consumer<List<CombatantEntry>>> onCombatantListChangeListener;
    private final List<Consumer<CombatantEntry>> onInitiativeChangeListener;

    public EventBus () {
        this.onTurnEndListener = new ArrayList<>();
        this.onCombatantListChangeListener = new ArrayList<>();
        this.onRoundEndListener = new ArrayList<>();
        this.onInitiativeChangeListener = new ArrayList<>();
    }

    //OnTurnEnd
    public void onTurnEnd () {
        onTurnEndListener.forEach(Runnable::run);
    }

    public void registerOnTurnEnd (Runnable runnableToRegister) {
        onTurnEndListener.add(runnableToRegister);
    }

    //OnRoundEnd
    public void onRoundEnd () {
        onRoundEndListener.forEach(Runnable::run);
    }

    public void registerOnRoundEnd (Runnable runnableToRegister) {
        onRoundEndListener.add(runnableToRegister);
    }

    //OnCombatantListChange
    public void onCombatantListChange (List<CombatantEntry> combatantEntryList) {
        onCombatantListChangeListener.forEach(listConsumer -> listConsumer.accept(combatantEntryList));
    }

    public void registerOnCombatantListChange (Consumer<List<CombatantEntry>> onChangeConsumer) {
        onCombatantListChangeListener.add(onChangeConsumer);
    }


    public void onInitiativeChange (CombatantEntry changedCombatantEntry) {
        onInitiativeChangeListener.forEach(consumer -> consumer.accept(changedCombatantEntry));
    }

    public void registerOnInitiativeChange (Consumer<CombatantEntry> consumerToRegister) {
        onInitiativeChangeListener.add(consumerToRegister);
    }

}
