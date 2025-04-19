package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.BillRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;

@NoArgsConstructor //этот конструктор точно нужен?
@AllArgsConstructor
@Service
public class BillService {

    private BillRepository billRepository; //private final

    public void save(Bill bill) {
        billRepository.save(bill);
    }

}
