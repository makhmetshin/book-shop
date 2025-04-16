package ru.ifellow.jschool.machmetshin.entity.good;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Good {
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private GoodType goodType;

    private int price;
    private String description;

}
