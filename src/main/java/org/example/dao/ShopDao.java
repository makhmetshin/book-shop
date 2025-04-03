package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Book;
import org.example.entity.Shop;

import java.util.Optional;
import java.util.Set;

public class ShopDao  {

    private DataSource dataSource = DataSource.getInstance();

    private final static ShopDao INSTANCE = new ShopDao();

    private ShopDao () {}

    public static ShopDao getInstance() {
        return new ShopDao();
//        return INSTANCE;
    }

    Set<Shop> shops = dataSource.getShops();

    public Set<Shop> findAll() {
        return shops;
    }
    public Optional<Shop> findById(Integer id) {

        for (Shop shop : shops)
            if(shop.getId().equals(id))
                return Optional.of(shop);

        return Optional.empty();
    }
}
