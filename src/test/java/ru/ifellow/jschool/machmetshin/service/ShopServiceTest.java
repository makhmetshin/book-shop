package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifellow.jschool.machmetshin.database.repository.ShopRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopService shopService;

    @Test
    public void findAllTest() {
        List<Shop> shops = new ArrayList<>();
        for(int i =0; i < 2; i ++)
            shops.add(new Shop());

        Mockito.doReturn(shops).when(shopRepository).findAll();
        Assertions.assertThat(shopService.findAll()).hasSize(2);
    }

    @Test
    public void findByIdTest() {
        Shop shop = new Shop();
        shop.setId(1);
        Mockito.doReturn(Optional.ofNullable(shop))
                .when(shopRepository).findById(1);

        Assertions.assertThat(shopService.findById(1).get().getId()).isEqualTo(1);
    }

}
