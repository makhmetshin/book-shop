package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "storageGoods")
@ToString(exclude = "storageGoods")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "storage_type")
@Entity
@Table(name = "storage")
public abstract class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "storage_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @OneToMany(mappedBy = "storage", orphanRemoval = true)
    List<StorageGood> storageGoods = new ArrayList<>();

    public Storage(Integer id, String address, String city, StorageType storageType) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.storageType = storageType;
    }
}
