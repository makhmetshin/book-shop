package ru.ifellow.jschool.machmetshin.entity.good;

import jakarta.persistence.*;
import lombok.*;

import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "orderItems")
@ToString(exclude = "orderItems")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "good")
public abstract class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "good_type")
    private GoodType goodType;

    @OneToMany(mappedBy = "good", orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "good", orphanRemoval = true)
    private List<StorageGood> storageGoods = new ArrayList<>();

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

}
