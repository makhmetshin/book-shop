package ru.ifellow.jschool.machmetshin.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class Order {
    private Integer orderId;
    private String customerFio;
    private Map<Integer, Integer> items;
    private LocalDate orderDate;
    private LocalDate arrivalDate;
    private Status status;
    private Integer totalPrice;
    private Integer departureWarehouse;
    private Integer arrivalShopId;

}
