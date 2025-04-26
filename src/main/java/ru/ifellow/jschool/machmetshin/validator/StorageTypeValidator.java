package ru.ifellow.jschool.machmetshin.validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;
import ru.ifellow.jschool.machmetshin.service.StorageService;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class StorageTypeValidator {

    private StorageService storageService;
    private EntityFoundByIdServiceValidator entityFoundByIdServiceValidator;

    public Storage validate(Integer storageId, StorageType expectedType)  {
        Storage storage = entityFoundByIdServiceValidator.validate(storageService, storageId, Storage.class);

        if (storage.getStorageType() != expectedType) {
            throw new IllegalArgumentException("Storage with id " + storageId + " is not of type " + expectedType);
        }
        return storage;
    }
}
