package ru.ifellow.jschool.machmetshin.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;
import ru.ifellow.jschool.machmetshin.service.StorageService;

import java.util.Optional;

@Component
@AllArgsConstructor
public class StorageTypeValidator {}

//    private final EntityExistsValidator entityExistsValidator;
//
//    public Storage validate(Optional<Storage> optionalStorage, Integer storageId, StorageType expectedType)  {
////        Storage storage;
////        switch (expectedType) {
////            case SHOP:
////                storage = entityExistsValidator.validate(optionalStorage, storageId, Shop.class);
////                break;
////            case WAREHOUSE: ;
////                break;
////            default:
////                throw new IllegalArgumentException("Invalid storage type: " + expectedType);
////        }
//        Storage storage = entityExistsValidator.validate(optionalStorage, storageId, Storage.class);
//
//        if (storage.getStorageType() != expectedType) {
//            throw new IllegalArgumentException("Storage with id " + storageId + " is not of type " + expectedType);
//        }
////        return storage;
//    }
//}
