package ru.ifellow.jschool.machmetshin.entity.good.book;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
public class Author {

    @Id
    private Integer id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books;

}
