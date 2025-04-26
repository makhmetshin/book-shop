//package ru.ifellow.jschool.machmetshin.database.repository;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//@DataJpaTest
//public class WarehouseBookRepositoryTest {
//
//    @Autowired
//    private WarehouseBookRepository warehouseBookRepository;
//
//    @Test
//    public void findAllTest() {
//        Assertions.assertThat(warehouseBookRepository.findAll()).hasSize(100);
//    }
//
//    @Test
//    public void findByIdTest() {
//        Assertions.assertThat(warehouseBookRepository.findById(1).get().getBook().getId()).isEqualTo(1);
//        Assertions.assertThat(warehouseBookRepository.findById(52).get().getBook().getId()).isEqualTo(2);
//        Assertions.assertThat(warehouseBookRepository.findById(100).get().getBook().getId()).isEqualTo(10);
//    }
//
//    @Test
//    public void findBySaveTest() {
//        warehouseBookRepository.save(new WarehouseBook());
//        Assertions.assertThat(warehouseBookRepository.findAll()).hasSize(101);
//    }
//
//    @Test
//    public void deleteTest() {
//        WarehouseBook warehouseBook = warehouseBookRepository.findById(1).get();
//        warehouseBookRepository.delete(warehouseBook);
//        Assertions.assertThat(warehouseBookRepository.findAll()).hasSize(99);
//    }
//
//    @Test
//    public void findByWarehouseIdAndBookIdTest() {
//        Assertions.assertThat(warehouseBookRepository.findByWarehouseIdAndBookId(1, 1).get().getId()).isEqualTo(1);
//        Assertions.assertThat(warehouseBookRepository.findByWarehouseIdAndBookId(5, 5).get().getId()).isEqualTo(45);
//        Assertions.assertThat(warehouseBookRepository.findByWarehouseIdAndBookId(10, 10).get().getId()).isEqualTo(100);
//    }
//}
