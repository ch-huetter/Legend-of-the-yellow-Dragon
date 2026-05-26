package de.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.game.bean.MessageResolvable;
import de.game.bean.enums.DashboardMessageUrgency;
import de.game.util.basic.BasicEmptyCheck;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
public class DashboardMessage implements MessageResolvable {

    @Id
    @Column(nullable = false)
    protected String messageKey;

    @JsonIgnore
    @Column(columnDefinition = "TEXT")
    protected String messageParam;

    @Nonnull
    protected Integer priority;

    @Enumerated(EnumType.STRING)
    protected DashboardMessageUrgency urgency;

    @Nonnull
    protected Boolean dismissable;

    @Nonnull
    private Boolean forPlayerCharacter;
    @Nonnull
    private Boolean forUser;

    @Column(name = "role_id")
    protected Integer minAuthorizedRoleId;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    protected Role role;

    /**
     * @return messageKey
     */
    @Override
    public String getKey () {
        return messageKey;
    }

    /**
     * @return messageParams
     */
    @Override
    public String[] getParams () {
        if (BasicEmptyCheck.isSet(messageParam)) {
            return messageParam.split(";");
        }
        return null;

    }
}
