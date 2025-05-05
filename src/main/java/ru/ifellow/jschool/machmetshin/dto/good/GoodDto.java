package ru.ifellow.jschool.machmetshin.dto.good;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.ifellow.jschool.machmetshin.entity.good.GoodType;

@Data
@EqualsAndHashCode
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GoodDto {

    private Integer id;
    private GoodType goodType;
    private int price;
    private String description;
}
