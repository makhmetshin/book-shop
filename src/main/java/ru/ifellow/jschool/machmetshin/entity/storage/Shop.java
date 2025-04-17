package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@NoArgsConstructor
@Entity
public class Shop extends Building implements Storage {

    public Shop(Integer id, String address, String city) {
        super(id, address, city);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Shop;
    }

}
