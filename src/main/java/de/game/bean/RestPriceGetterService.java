package de.game.bean;

import org.springframework.stereotype.Service;

@Service
public class RestPriceGetterService implements RestPriceGetter {

    @Override
    public Integer getRestPrice () {
        return 5;
    }
}
