package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@NoArgsConstructor
@Entity
public class Warehouse extends Building {

    public Warehouse(Integer id, String address, String city) {
        super(id, address, city);
    }

}
