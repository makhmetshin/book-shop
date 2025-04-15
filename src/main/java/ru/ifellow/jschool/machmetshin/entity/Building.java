package ru.ifellow.jschool.machmetshin.entity;

import lombok.*;

@Data
@NoArgsConstructor
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
