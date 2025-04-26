//package ru.ifellow.jschool.machmetshin.database.repository;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//@DataJpaTest
//public class ShopBookRepositoryTest {
//
//    @Autowired
//    private ShopBookRepository shopBookRepository;
//
//    @Test
//    public void findAllTest() {
//        Assertions.assertThat(shopBookRepository.findAll()).hasSize(100);
//    }
//
//    @Test
//    public void findByIdTest() {
//        Assertions.assertThat(shopBookRepository.findById(1).get().getBook().getId()).isEqualTo(1);
//        Assertions.assertThat(shopBookRepository.findById(52).get().getBook().getId()).isEqualTo(2);
//        Assertions.assertThat(shopBookRepository.findById(100).get().getBook().getId()).isEqualTo(10);
//    }
//
//    @Test
//    public void findBySaveTest() {
//        shopBookRepository.save(new ShopBook());
//        Assertions.assertThat(shopBookRepository.findAll()).hasSize(101);
//    }
//
//    @Test
//    public void deleteTest() {
//        ShopBook shopBook = shopBookRepository.findById(1).get();
//        shopBookRepository.delete(shopBook);
//        Assertions.assertThat(shopBookRepository.findAll()).hasSize(99);
//    }
//
//    @Test
//    public void findByShopIdAndBookIdTest() {
//        Assertions.assertThat(shopBookRepository.findByShopIdAndBookId(1, 1).get().getId()).isEqualTo(1);
//        Assertions.assertThat(shopBookRepository.findByShopIdAndBookId(5, 5).get().getId()).isEqualTo(45);
//        Assertions.assertThat(shopBookRepository.findByShopIdAndBookId(10, 10).get().getId()).isEqualTo(100);
//    }
//}
