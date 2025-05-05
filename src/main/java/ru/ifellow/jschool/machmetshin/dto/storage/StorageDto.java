package ru.ifellow.jschool.machmetshin.dto.storage;

import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;

@Data
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageDto {

    private Integer id;
    private String address;
    private String city;
    private StorageType storageType;
}
