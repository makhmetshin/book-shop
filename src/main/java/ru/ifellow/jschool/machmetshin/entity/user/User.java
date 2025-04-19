package ru.ifellow.jschool.machmetshin.entity.user;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = "orders")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "orders")
@Builder
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    private String name;
    private String surname;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<Order> orders = new ArrayList<>();
}
