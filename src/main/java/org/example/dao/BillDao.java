package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Bill;
import org.example.entity.ShopBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Optional;
import java.util.Set;

@Component
public class BillDao {
    @Autowired
    private DataSource dataSource;

    public BillDao () {}

    public Set<Bill> findAll() {
        return dataSource.getBills();
    }

    public void save(Bill bill) {
        dataSource.getBills().add(bill);
    }
}
