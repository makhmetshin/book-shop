package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ifellow.jschool.machmetshin.database.repository.BookRepository;
import ru.ifellow.jschool.machmetshin.database.repository.GoodRepository;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class GoodServiceTest {

    @Mock
    private GoodRepository goodRepository;


    @InjectMocks
    private GoodService goodService;

    private List<Good> goods = new ArrayList<>();

    @Test
    public void findAllTest() {
        for (int i = 0; i < 11; i++)
            goods.add(new Good() {
            });

        Mockito.doReturn(goods).when(goodRepository).findAll();
        Assertions.assertThat(goodService.findAll()).hasSize(11);
    }

    @Test
    public void findByIdTest() {
        Book book = Book.builder().genre("comedy").build();
        book.setPrice(100);

        Mockito.doReturn(Optional.ofNullable(book) )
                .when(goodRepository).findById(1);

        Assertions.assertThat(goodService.findById(1).get().getPrice())
                .isEqualTo(100);
    }
}
