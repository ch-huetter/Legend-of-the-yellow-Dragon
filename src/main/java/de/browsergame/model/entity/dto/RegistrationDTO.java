package de.browsergame.model.entity.dto;


import de.browsergame.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RegistrationDTO {
    private User user;

}
