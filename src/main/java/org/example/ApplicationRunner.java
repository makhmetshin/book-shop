package org.example;

import org.example.config.ApplicationConfiguration;

import org.example.dao.*;
import org.example.database.DataSource;
import org.example.entity.Bill;
import org.example.entity.Order;
import org.example.service.ShopService;
import org.example.service.WarehouseService;
import org.example.service.WebShopService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {

            var datasource = context.getBean("dataSource", DataSource.class);
            var billDao = context.getBean("billDao", BillDao.class);
//            System.out.println(datasource.getBills().size());
//            System.out.println(billDao.findAll().size());
//            System.out.println(billDao.findAll().size());
            var bookDao = context.getBean("bookDao", BookDao.class);
//            System.out.println(bookDao.findAll().size());
            var orderDao = context.getBean("orderDao", OrderDao.class);
//            orderDao.save(Order.builder().orderId(1).build());
//            System.out.println(orderDao.findById(1));
            var shopBookDao = context.getBean("shopBookDao", ShopBookDao.class);
//            System.out.println(shopBookDao.findAll().size());

            var warehouseBookDao = context.getBean("warehouseBookDao", WarehouseBookDao.class);
//            System.out.println(warehouseBookDao.findAll().size());

            var shopDao = context.getBean("shopDao", ShopDao.class);
//            System.out.println(shopDao.findAll().size());

            var  warehouseDao = context.getBean("warehouseDao", WarehouseDao.class);
//            System.out.println(warehouseDao.findAll().size());

            var  shopService = context.getBean("shopService", ShopService.class);
//            System.out.println(shopService.findAllShops().size());

//            shopService.transportBooksFromWarehouse(1, 1, 1,100);
//            System.out.println(shopService.findShopBookByIds(1,1).get());
//            System.out.println(shopService.findWarehouseBookByIds(1,1).get());

            var  warehouseService = context.getBean("warehouseService", WarehouseService.class);
//            System.out.println(warehouseService.findAllBooks(1).size());
            var  webShopService = context.getBean("webShopService", WebShopService.class);
//            orderDao.save(Order.builder().customerFio("petr").orderId(1).build());
//            orderDao.save(Order.builder().customerFio("petr").orderId(2).build());
//            System.out.println(orderDao.findAll());
//            System.out.println(orderDao.findByCustomerFio("petr"));
//            System.out.println(orderDao.findByCustomerFio("petr"));
//            System.out.println(webShopService.findOrdersByFio("petr"));
        }
    }
}
