package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Book;
import org.example.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Component
public class ShopDao  {

    private DataSource dataSource;
    private Set<Shop> shops;


    public ShopDao () {}

    public static ShopDao getInstance() {
        return null;
//        return INSTANCE;
    }

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
