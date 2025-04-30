package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true, exclude = {"orders"})
@ToString(callSuper=true, exclude = {"orders"})
@NoArgsConstructor
@Entity
@DiscriminatorValue("WAREHOUSE")
public class Warehouse extends Storage  {


    @OneToMany(mappedBy = "departureWarehouse", orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();


    public Warehouse(Integer id, String address, String city) {
        super(id, address, city, StorageType.WAREHOUSE);
    }

}
