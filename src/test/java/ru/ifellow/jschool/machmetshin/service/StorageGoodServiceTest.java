package ru.ifellow.jschool.machmetshin.service;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifellow.jschool.machmetshin.database.repository.StorageGoodRepository;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodGetAmountDto;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StorageGoodServiceTest {

    @Mock
    private StorageGoodRepository storageGoodRepository;
    @Mock
    private EntityExistsValidator entityExistsValidator;
    @Mock
    private StorageService storageService;
    @Mock
    private GoodService goodService;

    @InjectMocks
    @Spy
    private StorageGoodService spyStorageGoodService;

    @Test
    public void findAllTest() {
        List<StorageGood> storageGoods = new ArrayList<>();
        for(int i =0; i < 2; i ++)
            storageGoods.add(new StorageGood());

        Mockito.doReturn(storageGoods).when(storageGoodRepository).findAll();
        Assertions.assertThat(spyStorageGoodService.findAll()).hasSize(2);
    }

    @Test
    public void findByIdTest() {
        StorageGood storageGood = new StorageGood();
        storageGood.setId(1);
        Mockito.doReturn(Optional.ofNullable(storageGood))
                .when(storageGoodRepository).findById(1);

        Assertions.assertThat(spyStorageGoodService.findById(1).get().getId()).isEqualTo(1);
    }

    @Test
    public void removeGoodTest() {
        StorageGood storageGood = new StorageGood();
        storageGood.setId(1);
        storageGood.setQuantity(120);

        Mockito.doReturn(Optional.of(storageGood)).when(storageGoodRepository)
                .findByStorageIdAndGoodId(1,1);


        spyStorageGoodService.removeGood(new StorageGoodDto(1, 1, 100));

        Mockito.verify(storageGoodRepository).save(storageGood);
        Assertions.assertThat(storageGood.getQuantity()).isEqualTo(20);
    }

    @Test
    public void addGoodTest() {
        StorageGood storageGood = new StorageGood();
        storageGood.setId(1);
        storageGood.setQuantity(120);

        Mockito.doReturn(Optional.of(storageGood)).when(storageGoodRepository)
                .findByStorageIdAndGoodId(1,1);

        spyStorageGoodService.addGood(new StorageGoodDto(1, 1, 100));
        Assertions.assertThat(storageGood.getQuantity()).isEqualTo(220);
        Mockito.verify(storageGoodRepository).save(Mockito.eq(storageGood));

        spyStorageGoodService.addGood(new StorageGoodDto(2, 2, 500));
        StorageGood storageGood2 = new StorageGood();
        storageGood2.setQuantity(500);
        Mockito.verify(storageGoodRepository).save(Mockito.eq(storageGood2));
    }

    @Test
    public void addGoodsTest() {
        List<Good> goods = new ArrayList<>();
        for(int i =0; i < 4; i ++) {
            Book book = new Book();
            book.setId(i);
            goods.add(book);
        }
        goods.get(1).setId(0);

        spyStorageGoodService.addGoods(goods, 1);

        Mockito.verify(spyStorageGoodService).addGood(new StorageGoodDto(0, 1, 2));
        Mockito.verify(spyStorageGoodService).addGood(new StorageGoodDto(2, 1, 1));
        Mockito.verify(spyStorageGoodService).addGood(new StorageGoodDto(3, 1, 1));
    }

    @Test
    public void getAmountOfGoodTest() {

        assertThrows(EntityNotFoundException.class,
                () -> spyStorageGoodService.getAmountOfGood(new StorageGoodGetAmountDto(1,1, StorageType.SHOP)));

        StorageGood storageGood = new StorageGood();
        storageGood.setId(1);
        storageGood.setQuantity(120);
        Shop shop = new Shop();
        shop.setStorageType(StorageType.SHOP);
        storageGood.setStorage(shop);

        Mockito.doReturn(Optional.of(storageGood)).when(storageGoodRepository)
                .findByStorageIdAndGoodId(1,1);

        Assertions.assertThat(spyStorageGoodService.getAmountOfGood(new StorageGoodGetAmountDto(1,1, StorageType.SHOP)))
                .isEqualTo(120);

    }

    @Test
    public void findByStorageIdAndGoodIdTest() {
        StorageGood storageGood = new StorageGood();
        storageGood.setId(5);

        Mockito.doReturn(Optional.of(storageGood))
                .when(storageGoodRepository).findByStorageIdAndGoodId(1,1);
        Assertions.assertThat(
                spyStorageGoodService.findByStorageIdAndGoodId(1,1).get().getId())
                        .isEqualTo(5);
    }
}
