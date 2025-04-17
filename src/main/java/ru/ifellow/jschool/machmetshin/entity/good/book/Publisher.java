package ru.ifellow.jschool.machmetshin.entity.good.book;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Integer id;

    private String name;
    private String address;
    private String city;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private Set<Book> books;
}
