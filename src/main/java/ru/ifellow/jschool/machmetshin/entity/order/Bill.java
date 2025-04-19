package ru.ifellow.jschool.machmetshin.entity.order;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"order"})
@EqualsAndHashCode(exclude = {"order"})
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    private LocalDate date;


}
