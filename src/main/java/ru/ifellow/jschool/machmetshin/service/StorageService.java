package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.StorageRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.service.interfaces.Finder;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class StorageService implements Finder<Integer, Storage> {
    private final StorageRepository storageRepository;

    @Override
    public Optional<Storage> findById(Integer storageId) {
        return storageRepository.findById(storageId);
    }

    @Override
    public List<Storage> findAll() {
        return storageRepository.findAll();
    }
}
