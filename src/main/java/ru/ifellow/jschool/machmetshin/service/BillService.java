package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.dao.BillDao;
import ru.ifellow.jschool.machmetshin.entity.Bill;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class BillService {

    private BillDao billDao;

    public void save(Bill bill) {
        billDao.save(bill);
    }

}
