package ru.ifellow.jschool.machmetshin.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.StorageGoodRepository;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodDto;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StorageGoodService implements Findable<Integer, StorageGood> {

    private final StorageGoodRepository storageGoodRepository;
    private final GoodService goodService;
    private final StorageService storageService;
    private final EntityExistsValidator entityExistsValidator;

    @Override
    public List<StorageGood> findAll() {
        return storageGoodRepository.findAll();
    }

    @Override
    public Optional<StorageGood> findById(Integer id) {
        return storageGoodRepository.findById(id);
    }

    @Transactional
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

    @Transactional
    public void addGood(Integer goodId, Integer storageId, int amount) {
        StorageGood storageGood = storageGoodRepository.findByStorageIdAndGoodId(storageId, goodId)
                .map(sg -> {
                    sg.setQuantity(sg.getQuantity() + amount);
                    return sg;
                })
                // вот тут важно именно orElseGet, а не orElse!
                .orElseGet(() -> StorageGood.builder()
                        .storage(entityExistsValidator.validate(storageService.findById(storageId), storageId, Storage.class))
                        .good(entityExistsValidator.validate(goodService.findById(goodId), goodId, Good.class))
                        .quantity(amount)
                        .build());

        storageGoodRepository.save(storageGood);
    }

    @Transactional
    public void addGoods(List<Good> goods, Integer storageId) {
        goods.stream()
                .collect(Collectors.groupingBy(Good::getId, Collectors.summingInt(g -> 1)))
                .forEach((goodId, amount) -> addGood(goodId, storageId, amount));
    }

    public Integer getAmountOfGood(Integer goodId, Integer storageId, StorageType storageType) {

        return storageGoodRepository.findByStorageIdAndGoodId(storageId, goodId)
                .filter(sg -> sg.getStorage().getStorageType().equals(storageType))
                .map(StorageGood::getQuantity)
                .orElseThrow(() -> new EntityNotFoundException(("There is no such storage with id %d or such good " +
                        "with id $d in the storage or storage type is wrong").formatted(storageId, goodId)));

    }

    public Optional<StorageGood> findByStorageIdAndGoodId(Integer storageId, Integer goodId) {
        return storageGoodRepository.findByStorageIdAndGoodId(storageId, goodId);
    }

    public List<StorageGoodDto> findGoodIdsByStorageId(Integer storageId) {
        return storageGoodRepository.findByStorageId(storageId).stream()
                .map(sg -> new StorageGoodDto(sg.getGood().getId(), sg.getStorage().getId(), sg.getQuantity()))
                .collect(Collectors.toList());
    }


}
