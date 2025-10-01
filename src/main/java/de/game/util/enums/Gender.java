package de.game.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Sorted! Sequence is relevant for Database and Ui, only add values never delete or reorder them!
 */
@RequiredArgsConstructor
@Getter
public enum Gender {

    MALE("male"),
    FEMALE("female"),
    DIVERSE("diverse");

    final String name;
}
