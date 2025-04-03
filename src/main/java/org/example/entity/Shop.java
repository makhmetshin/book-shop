package org.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
public class Shop extends Building {
    public Shop(Integer id, String address, String city) {
        super(id, address, city);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Shop;
    }

}
