package org.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Warehouse extends Building {


    public Warehouse(Integer id, String address, String city) {
        super(id, address, city);
    }


}
