package de.game.bean.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AttributeDto {
    private String key;
    private int value;

}
