package de.game.util.enums;

import de.game.model.entity.PlayerCharacter;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Getter
public enum SessionAttributeEnum {

    PLAYER_CHARACTER("player_character", PlayerCharacter.class);

    private final String key;
    private final Class<?> cls;

    private SessionAttributeEnum (String key, Class<?> cls) {
        this.key = key;
        this.cls = cls;
    }

    @SuppressWarnings("unchecked")
    public <T> T get (HttpSession session) {
        Object value = session.getAttribute(this.getKey());
        if (value == null)
            return null;

        if (!cls.isInstance(value)) {
            throw new ClassCastException("Expected " + cls + " but got " + value.getClass());
        }
        return (T) value;
    }

    public void set (HttpSession session, Object value) {
        if (value != null && !cls.isInstance(value)) {
            throw new IllegalArgumentException("Expected " + cls + " but got " + value.getClass());
        }
        session.setAttribute(this.name(), value);
    }

}
