package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.database.repository.StorageRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;


import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class StorageService implements Findable<Integer, Storage> {
    private StorageRepository storageRepository;

    @Override
    public Optional<Storage> findById(Integer storageId) {
        return storageRepository.findById(storageId);
    }

    @Override
    public List<Storage> findAll() {
        return storageRepository.findAll();
    }
}
