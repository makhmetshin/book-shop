package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.BillRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;


@NoArgsConstructor
@AllArgsConstructor
@Service
public class BillService {

    private BillRepository billRepository;


    public void save(Bill bill) {
        billRepository.save(bill);
    }

}
