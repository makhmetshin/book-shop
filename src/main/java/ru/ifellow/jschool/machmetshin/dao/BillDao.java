package ru.ifellow.jschool.machmetshin.dao;

import ru.ifellow.jschool.machmetshin.database.DataSource;
import ru.ifellow.jschool.machmetshin.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Set;

@Component
public class BillDao {

    private DataSource dataSource;

    public BillDao () {}

    @Autowired
    public BillDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Set<Bill> findAll() {
        return dataSource.getBills();
    }

    public void save(Bill bill) {
        dataSource.getBills().add(bill);
    }
}
