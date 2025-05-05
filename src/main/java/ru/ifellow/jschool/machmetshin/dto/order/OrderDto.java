package ru.ifellow.jschool.machmetshin.dto.order;


import lombok.*;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private List<OrderItemDtoWithGoodDto> orderItemDtoWithGoodDtos;
    private LocalDate orderDate;
    private LocalDate arrivalDate;
    private OrderStatus orderStatus;
    private Integer totalPrice;
    private StorageDto departureWarehouseDto;
    private StorageDto arrivalShopDto;
    private Integer user_id;
}
