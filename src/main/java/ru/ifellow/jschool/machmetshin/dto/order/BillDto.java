package ru.ifellow.jschool.machmetshin.dto.order;

import lombok.*;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private Integer id;
    private Integer orderId;
    private StorageDto shopDto;
    private LocalDate date;
    private Boolean returned;

}
