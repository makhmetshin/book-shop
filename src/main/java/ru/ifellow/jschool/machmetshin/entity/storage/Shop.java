package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true, exclude = "shopBooks")
@ToString(callSuper=true, exclude = {"orders", "bills"})
@NoArgsConstructor
@Entity
@DiscriminatorValue("SHOP")
public class Shop extends Storage  {

    @OneToMany(mappedBy = "arrivalShop",  orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "shop",  orphanRemoval = true)
    private List<Bill> bills = new ArrayList<>();

    public Shop(Integer id, String address, String city) {
        super(id, address, city, StorageType.SHOP);
    }
}
