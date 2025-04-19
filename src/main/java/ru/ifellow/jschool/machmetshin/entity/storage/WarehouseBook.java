package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;


@NoArgsConstructor
//@EqualsAndHashCode(callSuper=true)
//@ToString(callSuper=true)
@EqualsAndHashCode( exclude = {"warehouse", "book"})
@ToString( exclude = {"warehouse", "book"})
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class WarehouseBook implements StorageGood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    private Integer bookAmount;

}
