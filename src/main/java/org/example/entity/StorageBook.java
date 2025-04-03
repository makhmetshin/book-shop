package org.example.entity;

import lombok.Builder;
import lombok.Data;

@Data
public abstract class StorageBook {

    private Integer storageId;
    private Integer bookId;
    private Integer bookAmount;

}
