package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class ShopBook extends StorageBook {

    private Integer storageId;
    private Integer bookId;
    private Integer bookAmount;


}
