package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.good.Book;


@NoArgsConstructor
//@EqualsAndHashCode(callSuper=true)
//@ToString(callSuper=true)
@EqualsAndHashCode
@ToString
@Table(
    uniqueConstraints = {
            @UniqueConstraint(columnNames = {"shop_id", "book_id"})
    }
)
@Entity
public class ShopBook{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    private Integer bookAmount;


//    @Builder
//    public ShopBook(Integer storageId, Integer bookId, Integer bookAmount) {
//        super(storageId, bookId, bookAmount);
//    }
}
