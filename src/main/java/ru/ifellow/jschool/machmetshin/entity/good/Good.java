package ru.ifellow.jschool.machmetshin.entity.good;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;
import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private GoodType goodType;

    @OneToMany(mappedBy = "good", orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();


    private int price;
    private String description;

}
