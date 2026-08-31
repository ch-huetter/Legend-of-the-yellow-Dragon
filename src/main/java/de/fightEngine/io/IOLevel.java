package de.fightEngine.io;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
/*
    IOLevels that get printed.

    Default is for printers only. Default means that the printer uses the ioManagers IOLevel instead of overriting it.
 */
public enum IOLevel {
    DEFAULT(0),
    NORMAL(1),
    DEBUG(10),
    TRACE(100);

    final int value;
}
