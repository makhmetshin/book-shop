package ru.ifellow.jschool.machmetshin.dto.storage;

import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageDto {

    private Integer id;
    private String address;
    private String city;
    private StorageType storageType;

    public StorageDto(Storage storage) {
        this.id = storage.getId();
        this.address = storage.getAddress();
        this.city = storage.getCity();
        this.storageType = storage.getStorageType();
    }
}
