package ru.ifellow.jschool.machmetshin.entity.order;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString(exclude = "orderItems")
@EqualsAndHashCode(exclude = "orderItems")
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
