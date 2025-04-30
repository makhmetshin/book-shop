package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import ru.ifellow.jschool.machmetshin.entity.good.Good;

@NoArgsConstructor
@EqualsAndHashCode(exclude = "storage")
@ToString(exclude = "good")
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"good_id", "storage_id"})
        },
        name = "storage_good"
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class StorageGood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    private Storage storage;

    @Column(name = "quantity")
    private Integer quantity;
}
