package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.BillRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.service.interfaces.Finder;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class BillService implements Finder<Integer, Bill> {

    private final BillRepository billRepository;

    @Transactional
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public Optional<Bill> findById(Integer id) {
        return billRepository.findById(id);
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }
}
