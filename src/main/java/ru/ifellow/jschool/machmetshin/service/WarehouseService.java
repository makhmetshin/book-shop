package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.WarehouseRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import ru.ifellow.jschool.machmetshin.service.interfaces.Finder;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class WarehouseService implements Finder<Integer, Warehouse> {

    private final WarehouseRepository warehouseRepository;

    @Override
    public Optional<Warehouse> findById(Integer id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }
}