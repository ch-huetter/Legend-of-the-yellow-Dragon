package de.game.view.uielements;

import de.game.util.PercentCalculator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Bar {
    private Integer value;
    private Integer max;
    private Integer percent;
    private String text;
    private String cssClass;

    public Bar (Integer value, Integer max, String text, String cssClass) {
        this.value = value;
        this.max = max;
        this.percent = PercentCalculator.calculatePercent(value, max);
        this.text = text;
        this.cssClass = cssClass;

    }
}
