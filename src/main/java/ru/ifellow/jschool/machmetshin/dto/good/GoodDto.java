package ru.ifellow.jschool.machmetshin.dto.good;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.good.GoodType;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GoodDto {

    private Integer id;
    private GoodType goodType;
    private int price;
    private String description;

    public GoodDto(Good good) {
        this.id = good.getId();
        this.goodType = good.getGoodType();
        this.price = good.getPrice();
        this.description = good.getDescription();

    }
}
