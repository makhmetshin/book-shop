package ru.ifellow.jschool.machmetshin.entity.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import ru.ifellow.jschool.machmetshin.entity.good.Good;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    private Integer id;
    private String customerFio;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    private LocalDate orderDate;
    private LocalDate arrivalDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "departure_warehouse_id", referencedColumnName = "id")
    private Warehouse departureWarehouse;

    @ManyToOne
    @JoinColumn(name = "arrival_shop_id", referencedColumnName = "id")
    private Shop arrivalShop;


}
