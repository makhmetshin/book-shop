package ru.ifellow.jschool.machmetshin.dto.order;

import lombok.*;
import ru.ifellow.jschool.machmetshin.dto.good.GoodDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDtoWithGoodId {

    private Integer goodId;
    private Integer quantity;
    private Integer priceAtPurchase;
}
