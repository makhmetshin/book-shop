package ru.ifellow.jschool.machmetshin.dto.storage;

import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageGoodGetAmountDto {

    private Integer goodId;
    private Integer storageId;
    private StorageType storageType;
}
