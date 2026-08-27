package de.game.bean.frontendComponents;


import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttributeSelectionEntry {
    private String key;
    private Short value;
    private Boolean canDecrease;
    private Short minValue;
}
