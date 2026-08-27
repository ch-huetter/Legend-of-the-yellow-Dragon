package de.fightEngine.io;

import de.game.bean.MessageResolvable;
import lombok.Getter;

@Getter
public abstract class IOManager {
    //TODO Methoden für debug ausgaben hinzufügen!
    protected final IOLevel messageIOLevel;

    public IOManager (IOLevel messageIOLevel) {
        this.messageIOLevel = messageIOLevel;
    }
    
    public abstract void printMsg (String msg);

    public abstract void printDebugMsg (String msg);

    public abstract void printTraceMsg (String msg);

    public abstract void printMsgResolvable (MessageResolvable messageResolvable);

    public abstract void printDebugMsgResolvable (MessageResolvable messageResolvable);

    public abstract void printTraceMsgResolvable (MessageResolvable messageResolvable);

    public abstract String getInput ();

}
