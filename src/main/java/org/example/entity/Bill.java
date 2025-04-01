package org.example.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class Bill {

    private Integer id;
    private Integer shopId;
    private Map<Integer, Integer> items;
    private String customerFio;
    private LocalDate date;
    private Integer summa;

}
