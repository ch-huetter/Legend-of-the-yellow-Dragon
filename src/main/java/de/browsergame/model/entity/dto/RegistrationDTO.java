package de.browsergame.model.entity.dto;


import de.browsergame.model.entity.User;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RegistrationDTO {
    @Valid
    private User user;

}
