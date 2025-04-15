package ru.ifellow.jschool.machmetshin.dao;

import ru.ifellow.jschool.machmetshin.database.DataSource;
import ru.ifellow.jschool.machmetshin.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
@Component
public class ShopDao  {

    private DataSource dataSource;
    public ShopDao () {}

    @Autowired
    public ShopDao (DataSource dataSource) {
        this.dataSource = dataSource;
    }



    public Set<Shop> findAll() {
        return dataSource.getShops();
    }
    public Optional<Shop> findById(Integer id) {

        for (Shop shop : dataSource.getShops())
            if(shop.getId().equals(id))
                return Optional.of(shop);

        return Optional.empty();
    }
}
