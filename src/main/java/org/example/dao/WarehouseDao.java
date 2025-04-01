package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Warehouse;

import java.util.Optional;
import java.util.Set;

public class WarehouseDao {

    private DataSource dataSource = DataSource.getInstance();

    private final static  WarehouseDao INSTANCE = new WarehouseDao();

    private WarehouseDao() {}

    public static WarehouseDao getInstance() {
        return new WarehouseDao();
//        return INSTANCE;
    }

    public Set<Warehouse> findAll(Integer warehouseId) {
        return  dataSource.getWarehouses();
    }

    public Optional<Warehouse> findById(Integer warehouseId) {
        return  dataSource.getWarehouses()
                .stream()
                .filter(warehouse -> warehouse.getId().equals(warehouseId))
                .findFirst();
    }

}
