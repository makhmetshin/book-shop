package ru.ifellow.jschool.machmetshin.dto.order;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateWebOrderDto extends CreateOrderDto {

    Integer warehouseId;
}
