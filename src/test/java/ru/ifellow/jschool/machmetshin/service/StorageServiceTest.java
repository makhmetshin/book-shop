package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifellow.jschool.machmetshin.database.repository.StorageRepository;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StorageServiceTest {

    @Mock
    private StorageRepository storageRepository;


    @InjectMocks
    private StorageService storageService;

    private List<Storage> storages = new ArrayList<>();

    @Test
    public void findAllTest() {
        for (int i = 0; i < 11; i++)
            storages.add(new Shop());

        Mockito.doReturn(storages).when(storageRepository).findAll();
        Assertions.assertThat(storageService.findAll()).hasSize(11);
    }

    @Test
    public void findByIdTest() {
        Storage storage = new Shop();
        storage.setCity("city");

        Mockito.doReturn(Optional.ofNullable(storage) )
                .when(storageRepository).findById(1);

        Assertions.assertThat(storageService.findById(1).get().getCity())
                .isEqualTo("city");
    }
}
