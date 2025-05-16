package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.GoodRepository;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.service.interfaces.Finder;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class GoodService implements Finder<Integer, Good> {
    private final GoodRepository goodRepository;

    @Override
    public Optional<Good> findById(Integer goodId) {
        return goodRepository.findById(goodId);
    }
    @Override
    public List<Good> findAll() {
        return goodRepository.findAll();
    }
}
