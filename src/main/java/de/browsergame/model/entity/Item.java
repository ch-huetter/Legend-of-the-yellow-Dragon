package de.browsergame.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Item {

    @Id
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NonNull
    private Boolean visible;

    @ManyToOne()
    @JoinColumn(name = "equipmentSlot_id", referencedColumnName = "id")
    private EquipmentSlot equipmentSlot;

}
