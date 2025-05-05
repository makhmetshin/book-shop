package ru.ifellow.jschool.machmetshin.dto.order;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {
    Integer userId;
    Integer receiveStorageId;
    List<OrderItemDtoWithGoodId> orderItemDtosWithGoodId;
}
