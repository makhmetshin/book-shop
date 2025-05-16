package ru.ifellow.jschool.machmetshin.dto.order;

import lombok.*;
import ru.ifellow.jschool.machmetshin.dto.good.GoodDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDtoWithGoodDto {

    private GoodDto goodDto;
    private Integer quantity;
    private Integer priceAtPurchase;
}
