package de.game.util.attribute.valueCalculator;

import de.game.model.entity.Monster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterValueCalculator implements AttributeValueCalculator<Monster> {
    //TODO richtige Formeln hier einfügen.
    //TODO System erarbeiten für Monster Skalierung und unterschiedliche Monsterstärken, Bosse besondere Monster etc

    @Override
    public void calculateEntityValues (Monster entity) {

    }

    @Override
    public Class<Monster> getSupportedClass () {
        return null;
    }
    /*
    Implement Logic for Calculating Monster Stats. While Player Stats calculate the same for every Player, Monsters will probably scale differently
     */
}
