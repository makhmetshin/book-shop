package ru.ifellow.jschool.machmetshin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class Warehouse extends Building {


    public Warehouse(Integer id, String address, String city) {
        super(id, address, city);
    }


}
