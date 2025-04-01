package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Bill;
import org.example.entity.ShopBook;

import java.util.Optional;
import java.util.Set;

public class BillDao {
    private DataSource dataSource = DataSource.getInstance();

    private final static BillDao INSTANCE = new BillDao();
    private Set<Bill> bills = dataSource.getBills();
    private BillDao () {}

    public static BillDao getInstance() {
        return new BillDao();
//        return INSTANCE;
    }

    public Set<Bill> findAll() {
        return bills;
    }

    public void save(Bill bill) {
        bills.add(bill);
    }
}
