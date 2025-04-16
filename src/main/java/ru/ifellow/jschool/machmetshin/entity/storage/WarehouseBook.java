package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.good.Book;


@NoArgsConstructor
//@EqualsAndHashCode(callSuper=true)
//@ToString(callSuper=true)
@EqualsAndHashCode
@ToString
@Entity
public class WarehouseBook {

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
//    @Builder
//    public WarehouseBook(Integer storageId, Integer bookId, Integer bookAmount) {
//        super(storageId, bookId, bookAmount);
//    }
}
