package ru.ifellow.jschool.machmetshin.entity.order;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import ru.ifellow.jschool.machmetshin.entity.user.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString(exclude = {"orderItems", "bill", "user"})
@EqualsAndHashCode(exclude = {"orderItems", "bill", "user"})
@Table(name = "Orders") //наименования таблиц лучше с маленькой буквы
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
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

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Bill bill;
}
