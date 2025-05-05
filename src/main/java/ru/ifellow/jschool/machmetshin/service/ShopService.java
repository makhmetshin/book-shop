package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.ShopRepository;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ShopService implements Findable<Integer, Shop> {

    private final ShopRepository shopRepository;
    private final EntityExistsValidator entityExistsValidator;

    @Override
    public Optional<Shop> findById(Integer id) {
        return shopRepository.findById(id);
    }

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    public List<StorageDto> findAllDto() {
        return shopRepository.findAll().stream()
                .map(shop -> new StorageDto(shop.getId(), shop.getAddress(), shop.getCity(), shop.getStorageType()))
                .collect(Collectors.toList());
    }

    public StorageDto findByIdDto(Integer id) {
        Shop shop = entityExistsValidator.validate(shopRepository.findById(id), id, Shop.class);
        return new StorageDto(shop.getId(), shop.getAddress(), shop.getCity(), shop.getStorageType());
    }
}
