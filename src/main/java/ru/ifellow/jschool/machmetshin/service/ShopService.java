package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.database.repository.ShopRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;


import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ShopService implements Findable<Integer, Shop> {

    private ShopRepository shopRepository;

    @Override
    public Optional<Shop> findById(Integer id) {
        return shopRepository.findById(id);
    }

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }
}
