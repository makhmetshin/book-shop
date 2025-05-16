package ru.ifellow.jschool.machmetshin.dto.order;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnGoodsOrderDto {
    private Integer id;
    private List<OrderItemDtoWithGoodDto> orderItemDtoWithGoodDtos;
}
