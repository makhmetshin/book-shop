package ru.ifellow.jschool.machmetshin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;


//import ru.ifellow.jschool.machmetshin.dao.*;
//import ru.ifellow.jschool.machmetshin.database.DataSource;
//import ru.ifellow.jschool.machmetshin.service.ShopService;
//import ru.ifellow.jschool.machmetshin.service.WarehouseService;
//import ru.ifellow.jschool.machmetshin.service.WebShopService;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ifellow.jschool.machmetshin.database.repository.BookRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

@SpringBootApplication
@AllArgsConstructor
public class ApplicationRunner implements CommandLineRunner {


    private final BookRepository bookRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
//        bookRepository.findB
        var smth = bookRepository.findAll().stream().toList();
        System.out.println(smth);

        System.out.println("Все книги в базе данных:");
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println(">>> Таблицы в базе данных:");
            try (ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
                while (tables.next()) {
                    String schema = tables.getString("TABLE_SCHEM");
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.printf("Схема: %s, Таблица: %s%n", schema, tableName);
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(ApplicationRunner.class, args);
        System.out.println("Программа начала выполнение.");
//        Thread.sleep(100_000);

//        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {

//            var datasource = context.getBean("dataSource", DataSource.class);
//            var billDao = context.getBean("billDao", BillDao.class);
////            System.out.println(datasource.getBills().size());
////            System.out.println(billDao.findAll().size());
////            System.out.println(billDao.findAll().size());
//            var bookDao = context.getBean("bookDao", BookDao.class);
////            System.out.println(bookDao.findAll().size());
//            var orderDao = context.getBean("orderDao", OrderDao.class);
////            orderDao.save(Order.builder().orderId(1).build());
////            System.out.println(orderDao.findById(1));
//            var shopBookDao = context.getBean("shopBookDao", ShopBookDao.class);
////            System.out.println(shopBookDao.findAll().size());
//
//            var warehouseBookDao = context.getBean("warehouseBookDao", WarehouseBookDao.class);
////            System.out.println(warehouseBookDao.findAll().size());
//
//            var shopDao = context.getBean("shopDao", ShopDao.class);
////            System.out.println(shopDao.findAll().size());
//
//            var  warehouseDao = context.getBean("warehouseDao", WarehouseDao.class);
////            System.out.println(warehouseDao.findAll().size());
//
//            var  shopService = context.getBean("shopService", ShopService.class);
////            System.out.println(shopService.findAllShops().size());
//
////            shopService.transportBooksFromWarehouse(1, 1, 1,100);
////            System.out.println(shopService.findShopBookByIds(1,1).get());
////            System.out.println(shopService.findWarehouseBookByIds(1,1).get());
//
//            var  warehouseService = context.getBean("warehouseService", WarehouseService.class);
////            System.out.println(warehouseService.findAllBooks(1).size());
//            var  webShopService = context.getBean("webShopService", WebShopService.class);
////            orderDao.save(Order.builder().customerFio("petr").orderId(1).build());
////            orderDao.save(Order.builder().customerFio("petr").orderId(2).build());
////            System.out.println(orderDao.findAll());
////            System.out.println(orderDao.findByCustomerFio("petr"));
////            System.out.println(orderDao.findByCustomerFio("petr"));
////            System.out.println(webShopService.findOrdersByFio("petr"));
//        }
    }
}
