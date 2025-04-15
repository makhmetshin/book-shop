package ru.ifellow.jschool.machmetshin.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class WarehouseBook extends StorageBook {

    private Integer storageId;
    private Integer bookId;
    private Integer bookAmount;

}
