package ru.ifellow.jschool.machmetshin.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.database.repository.StorageGoodRepository;
import ru.ifellow.jschool.machmetshin.database.repository.UserRepository;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;
import ru.ifellow.jschool.machmetshin.validator.EntityFoundByIdServiceValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class StorageGoodService implements Findable<Integer, StorageGood> {

    private StorageGoodRepository storageGoodRepository;
    private GoodService goodService;
    private StorageService storageService;
    private EntityFoundByIdServiceValidator entityFoundByIdServiceValidator;

    @Override
    public List<StorageGood> findAll() {
        return storageGoodRepository.findAll();
    }

    @Override
    public Optional<StorageGood> findById(Integer id) {
        return storageGoodRepository.findById(id);
    }

    public void removeGood(Integer goodId, Integer storageId, int amount) {


        StorageGood storageGood = storageGoodRepository.findByStorageIdAndGoodId(storageId, goodId)
                .orElseThrow(() -> new EntityNotFoundException("Good not found in this storage"));

        int quantity = storageGood.getQuantity();

        if (quantity < amount)
            throw new IllegalStateException("Not enough goods in this storage");
        else {
            storageGood.setQuantity(quantity - amount);
            storageGoodRepository.save(storageGood);
        }
    }

    public void addGood(Integer goodId, Integer storageId, int amount) {
        StorageGood storageGood;
        Optional<StorageGood> optionalStorageGood = storageGoodRepository.findByStorageIdAndGoodId(storageId, goodId);

        if (optionalStorageGood.isPresent()) {
            storageGood = optionalStorageGood.get();
            storageGood.setQuantity(storageGood.getQuantity() + amount);
        }
        else
            storageGood = StorageGood.builder()
                    .storage(entityFoundByIdServiceValidator.validate(storageService, storageId, Storage.class)
//                            storageService.findById(storageId).orElseThrow(() -> new EntityNotFoundException("There is no such shop with id %d".formatted(storageId)))
                    )
                    .good(entityFoundByIdServiceValidator.validate(goodService, goodId, Good.class)
//                            goodService.findById(goodId).orElseThrow(() -> new EntityNotFoundException("There is no such book with id %d".formatted(storageId)))
                    )
                    .quantity(amount)
                    .build();

        storageGoodRepository.save(storageGood);
    }

    public void addGoods(List<Good> goods, Integer storageId) {
        Map<Integer, Integer> goodCountMap = new HashMap<>();

        for (Good good : goods)
            goodCountMap.put(good.getId(), goodCountMap.getOrDefault(good.getId(), 0) + 1);

        for (Map.Entry<Integer, Integer> entry : goodCountMap.entrySet()) {
            Integer goodId = entry.getKey();
            Integer amount = entry.getValue();
            addGood(goodId, storageId, amount);
        }
    }

    public Integer getAmountOfGood(Integer goodId, Integer storageId) {
        Optional<StorageGood> optionalStorageGood = storageGoodRepository
                .findByStorageIdAndGoodId(goodId, storageId);

        if (optionalStorageGood.isPresent()) return  optionalStorageGood.get().getQuantity();
        else throw new EntityNotFoundException(
                "There is no such shop with id %d or such book with id $d in the shop".formatted(storageId, goodId));
    }

    public Optional<StorageGood> findByStorageIdAndGoodId(Integer storageId, Integer goodId) {
        return storageGoodRepository.findByStorageIdAndGoodId(storageId, goodId);
    }


}
