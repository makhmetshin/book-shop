package ru.ifellow.jschool.machmetshin.entity.order;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.good.Good;

@Entity
@EqualsAndHashCode(exclude = "order")
@Getter
@Setter
@ToString(exclude = "order")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private Good good;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_at_purchase")
    private Integer priceAtPurchase;
}