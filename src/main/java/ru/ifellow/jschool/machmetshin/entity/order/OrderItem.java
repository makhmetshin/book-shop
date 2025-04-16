package ru.ifellow.jschool.machmetshin.entity.order;

import jakarta.persistence.*;
import ru.ifellow.jschool.machmetshin.entity.good.Good;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Good good;

    private Integer quantity;
    private Integer priceAtPurchase;
}