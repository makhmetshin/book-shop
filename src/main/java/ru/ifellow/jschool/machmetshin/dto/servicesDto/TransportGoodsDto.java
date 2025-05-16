package ru.ifellow.jschool.machmetshin.dto.servicesDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportGoodsDto {
    private Integer goodId;
    private Integer departureStorageId;
    private Integer arrivalStorageId;
    private Integer amount;
}
