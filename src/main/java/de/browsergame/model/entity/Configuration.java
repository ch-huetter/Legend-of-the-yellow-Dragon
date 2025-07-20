package de.browsergame.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "configuration")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Configuration {

    @Id
    private String parameter;

    private String value;

}
