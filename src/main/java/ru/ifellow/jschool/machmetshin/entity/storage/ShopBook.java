package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.*;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;


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
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class ShopBook implements StorageGood{

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



}
