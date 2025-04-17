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
public class Bill {
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    private String customerFio;
    private LocalDate date;
    private Integer summa;

}
