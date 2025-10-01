package de.game.controller.dto;


import de.game.configuration.annotation.ValidRegistrationDTO;
import de.game.model.entity.User;
import de.game.util.enums.Gender;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ValidRegistrationDTO
public class RegistrationDto {
    @Valid
    private User user;

    private List<Gender> genderList;
}
