package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.database.repository.ShopRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ShopService {

    private ShopRepository shopRepository;

    public Optional<Shop> findById(Integer id) {
        return shopRepository.findById(id);
    }
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }
}
