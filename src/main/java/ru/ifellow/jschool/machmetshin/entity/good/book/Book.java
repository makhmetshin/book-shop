package ru.ifellow.jschool.machmetshin.entity.good.book;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.good.Good;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name="id")
@Table(name = "book")
public class Book extends Good {

    @Column(name = "isbn")
    private String ISBN;

    @Column(name = "title")
    private String title;

    @Column(name = "pages_amount")
    private Integer pagesAmount;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    @Column(name = "genre")
    private String genre;

    @Column(name = "published_date")
    private LocalDate publishedDate;

}
