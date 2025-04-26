package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.storage.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StorageManagerServiceTest {

    @Mock
    private StorageGoodService storageGoodService;
    @Mock
    private GoodService goodService ;

    @InjectMocks
    @Spy
    private StorageManagerService spyStorageManagerService;

    @Test
    public void findStorageGoodsByStorageTypeTest() {
        List<StorageGood> storageGoods = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Good good = Book.builder().title("title" + i).build();
            Storage storage = new Shop();
            storage.setStorageType(StorageType.SHOP);
            storageGoods.add(new StorageGood(i, good, storage, 10));
        }
        for(int i = 0; i < 4; i++) {
            Good good = Book.builder().title("title" + i).build();
            Storage storage = new Warehouse();
            storage.setStorageType(StorageType.WAREHOUSE);
            storageGoods.add(new StorageGood(5 + i, good, storage, 10));
        }
        Mockito.doReturn(storageGoods).when(storageGoodService).findAll();

        Assertions.assertThat(spyStorageManagerService.findStorageGoodsByStorageType(StorageType.SHOP))
                .hasSize(5);
        Assertions.assertThat(spyStorageManagerService.findStorageGoodsByStorageType(StorageType.WAREHOUSE))
                .hasSize(4);
    }

    @Test
    public void findAllGoodsTest() {
        List<Good> goods = new ArrayList<>();
        List<StorageGood> storageGoods = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Good good = Book.builder().title("title" + i).build();
            good.setId(i);
            goods.add(good);
            Storage storage = new Shop(i, "city" + i, "address" + i);

            storageGoods.add(StorageGood.builder()
                    .good(good)
                    .storage(storage)
                    .build());
        }

        Mockito.doReturn(goods).when(goodService).findAll();
        Mockito.doReturn(storageGoods).when(spyStorageManagerService)
                .findStorageGoodsByStorageType(StorageType.SHOP);

        Assertions.assertThat(spyStorageManagerService.findAllGoods(4, StorageType.SHOP))
                .hasSize(1);
    }

}
