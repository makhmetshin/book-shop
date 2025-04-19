package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true, exclude = "shopBooks")
@ToString(callSuper=true)
@NoArgsConstructor
@Entity
public class Shop extends Building implements Storage {

    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ShopBook> shopBooks = new ArrayList<>();

    @OneToMany(mappedBy = "arrivalShop",  orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "shop",  orphanRemoval = true)
    private List<Bill> bills = new ArrayList<>();

    public Shop(Integer id, String address, String city) {
        super(id, address, city);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Shop;
    }

}
