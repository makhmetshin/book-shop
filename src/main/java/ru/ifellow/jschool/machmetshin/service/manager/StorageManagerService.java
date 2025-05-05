package ru.ifellow.jschool.machmetshin.service.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;
import ru.ifellow.jschool.machmetshin.service.GoodService;
import ru.ifellow.jschool.machmetshin.service.StorageGoodService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class StorageManagerService {

    private final StorageGoodService storageGoodService;
    private final GoodService goodService ;

    public List<StorageGood> findStorageGoodsByStorageType(StorageType storageType) {

        return storageGoodService.findAll().stream()
                .filter(it -> it.getStorage().getStorageType().equals(storageType))
                .collect(Collectors.toList());
    }

    public Set<Good> findAllGoods(Integer storageId, StorageType storageType)  {
        List<Good> goods = goodService.findAll();
        List<StorageGood> concreteStorageGoods = findStorageGoodsByStorageType(storageType);

        Set<Integer> goodsIdsInStorage =  concreteStorageGoods.stream()
                .filter(it -> it.getStorage().getId().equals(storageId))
                .map(it -> it.getGood().getId())
                .collect(Collectors.toSet());

        return goods.stream()
                .filter(good -> goodsIdsInStorage.contains(good.getId()))
                .collect(Collectors.toSet());
    }
}
