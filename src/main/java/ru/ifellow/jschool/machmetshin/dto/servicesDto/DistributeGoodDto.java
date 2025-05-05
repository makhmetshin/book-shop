package ru.ifellow.jschool.machmetshin.dto.servicesDto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Data
@EqualsAndHashCode
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DistributeGoodDto {
    private Integer goodId;
    private List<Integer> shopIds;
    private Integer warehouseId;
    private Integer amount;
}
