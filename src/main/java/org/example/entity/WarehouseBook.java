package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WarehouseBook implements StorageBook {

    private Integer storageId;
    private Integer bookId;
    private Integer bookAmount;

}
