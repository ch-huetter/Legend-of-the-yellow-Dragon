package de.fightEngine.io;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IOLevel {
    NORMAL(1),
    DEBUG(10),
    TRACE(100);

    final int value;
}
