package ru.ifellow.jschool.machmetshin.dto.order;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class CreateWebOrderDto extends CreateOrderDto {

    private Integer warehouseId;

    public CreateWebOrderDto(Integer userId, Integer receiveStorageId, List<OrderItemDtoWithGoodId> orderItemDtosWithGoodId, Integer warehouseId) {
        super(userId, receiveStorageId, orderItemDtosWithGoodId);
        this.warehouseId = warehouseId;
    }
}
