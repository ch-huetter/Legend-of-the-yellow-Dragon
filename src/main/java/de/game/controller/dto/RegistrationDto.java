package de.game.controller.dto;


import de.game.configuration.annotation.ValidRegistrationDTO;
import de.game.model.entity.User;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ValidRegistrationDTO
public class RegistrationDto {
    @Valid
    private User user;

}
