package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true, exclude = {"warehouseBooks", "orders"})
@ToString(callSuper=true, exclude = {"warehouseBooks", "orders"})
@NoArgsConstructor
@Entity
public class Warehouse extends Building implements Storage {


    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WarehouseBook> warehouseBooks = new ArrayList<>();

    @OneToMany(mappedBy = "departureWarehouse", orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();


    public Warehouse(Integer id, String address, String city) {
        super(id, address, city);
    }

}
