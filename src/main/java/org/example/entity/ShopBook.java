package org.example.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopBook {

    private Integer shopId;
    private Integer bookId;
    private Integer bookAmount;

}
