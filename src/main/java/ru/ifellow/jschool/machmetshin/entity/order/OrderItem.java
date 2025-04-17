package ru.ifellow.jschool.machmetshin.entity.order;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.ifellow.jschool.machmetshin.entity.good.Good;

@Entity
@EqualsAndHashCode
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private Good good;

    private Integer quantity;
    private Integer priceAtPurchase;
}