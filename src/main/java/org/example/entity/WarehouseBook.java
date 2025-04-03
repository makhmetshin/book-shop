package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class WarehouseBook extends StorageBook {

    private Integer storageId;
    private Integer bookId;
    private Integer bookAmount;

}
