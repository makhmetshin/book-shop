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
@EqualsAndHashCode(callSuper = true, exclude = {"shopBooks", "warehouseBooks"})
@ToString(callSuper = true, exclude = {"shopBooks", "warehouseBooks"})
@PrimaryKeyJoinColumn(name="id")
public class Book extends Good {

    private String ISBN;
    private String title;
    private Integer pagesAmount;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    private String genre;
    private LocalDate publishedDate;

}
