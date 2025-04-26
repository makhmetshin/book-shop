package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.BillRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;


import java.util.List;
import java.util.Optional;

@NoArgsConstructor //этот конструктор точно нужен?
@AllArgsConstructor
@Service
public class BillService implements Findable<Integer, Bill> {

    private BillRepository billRepository; //private final

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
