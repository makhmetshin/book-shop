package ru.ifellow.jschool.machmetshin.entity.storage;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "storage_type")
@Entity
public abstract class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
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
