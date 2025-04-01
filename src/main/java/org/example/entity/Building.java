package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Building {
    private Integer id;
    private String address;
    private String city;

    public Building(Integer id, String address, String city) {
        this.id = id;
        this.address = address;
        this.city = city;
    }
}
