package ru.ifellow.jschool.machmetshin.entity.good.book;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String city;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private Set<Book> books;
}
