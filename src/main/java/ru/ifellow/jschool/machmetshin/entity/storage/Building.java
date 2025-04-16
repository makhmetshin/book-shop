package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@MappedSuperclass
public abstract class Building {
    @Id
    private Integer id;
    private String address;
    private String city;
}
