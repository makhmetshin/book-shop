package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifellow.jschool.machmetshin.database.repository.WarehouseRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {
    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private WarehouseService warehouseService;

    @Test
    public void findAllTest() {
        List<Warehouse> warehouses = new ArrayList<>();
        for(int i =0; i < 2; i ++)
            warehouses.add(new Warehouse());

        Mockito.doReturn(warehouses).when(warehouseRepository).findAll();
        Assertions.assertThat(warehouseService.findAll()).hasSize(2);
    }

    @Test
    public void findByIdTest() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1);
        Mockito.doReturn(Optional.ofNullable(warehouse))
                .when(warehouseRepository).findById(1);

        Assertions.assertThat(warehouseService.findById(1).get().getId()).isEqualTo(1);
    }
}
