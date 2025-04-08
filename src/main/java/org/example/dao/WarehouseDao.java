package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
@Component
public class WarehouseDao {

    private DataSource dataSource;

    public WarehouseDao() {}

    @Autowired
    public WarehouseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static WarehouseDao getInstance() {
        return new WarehouseDao();
//        return INSTANCE;
    }

    public Set<Warehouse> findAll() {
        return  dataSource.getWarehouses();
    }

    public Optional<Warehouse> findById(Integer warehouseId) {
        return  dataSource.getWarehouses()
                .stream()
                .filter(warehouse -> warehouse.getId().equals(warehouseId))
                .findFirst();
    }

}
