package ru.ifellow.jschool.machmetshin.dto.storage;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageGoodDto {

    private Integer goodId;
    private Integer storageId;
    private Integer quantity;
}
