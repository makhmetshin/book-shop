package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.database.repository.WarehouseRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseService {

    private WarehouseRepository warehouseRepository;

    public Optional<Warehouse> findById(Integer id) {
        return warehouseRepository.findById(id);
    }
}