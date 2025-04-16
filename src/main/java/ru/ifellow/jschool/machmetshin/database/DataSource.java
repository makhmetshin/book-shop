//package ru.ifellow.jschool.machmetshin.database;
//
//import lombok.Data;
//import org.springframework.stereotype.Component;
//import ru.ifellow.jschool.machmetshin.entity.*;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@Component
//public class DataSource {
//
//    private Set<Book> books = new HashSet<>();
//    private Set<Shop> shops = new HashSet<>();
//    private Set<Warehouse> warehouses = new HashSet<>();
//    private Set<Bill> bills = new HashSet<>();
//    private Set<StorageBook> shopBooks = new HashSet<>();
//    private Set<StorageBook> warehouseBooks = new HashSet<>();
//    private Set<Order> orders = new HashSet<>();
//
//    public DataSource() {
//
//    }
//
//    {
//
//        books.add(new Book(1, "978-3-16-148410-0", "Book One", "Author One", "Publisher One", 200, "Fiction", "Description of Book One", LocalDate.of(2020, 1, 15)));
//        books.add(new Book(2, "978-3-16-148410-1", "Book Two", "Author Two", "Publisher Two", 250, "Non-Fiction", "Description of Book Two", LocalDate.of(2021, 5, 20)));
//        books.add(new Book(3, "978-3-16-148410-2", "Book Three", "Author Three", "Publisher Three", 300, "Fantasy", "Description of Book Three", LocalDate.of(2019, 8, 10)));
//        books.add(new Book(4, "978-3-16-148410-3", "Book Four", "Author Three", "Publisher Four", 150, "Science Fiction", "Description of Book Four", LocalDate.of(2022, 3, 5)));
//        books.add(new Book(5, "978-3-16-148410-4", "Book Five", "Author Three", "Publisher Five", 180, "Romance", "Description of Book Five", LocalDate.of(2020, 11, 25)));
//        books.add(new Book(6, "978-3-16-148410-5", "Book Six", "Author Six", "Publisher Six", 220, "Romance", "Description of Book Six", LocalDate.of(2018, 6, 30)));
//        books.add(new Book(7, "978-3-16-148410-6", "Book Seven", "Author Seven", "Publisher Seven", 190, "Biography", "Description of Book Seven", LocalDate.of(2021, 9, 12)));
//        books.add(new Book(8, "978-3-16-148410-7", "Book Eight", "Author Eight", "Publisher Eight", 210, "Biography", "Description of Book Eight", LocalDate.of(2017, 2, 14)));
//        books.add(new Book(9, "978-3-16-148410-8", "Book Nine", "Author Nine", "Publisher Nine", 230, "Cookbook", "Description of Book Nine", LocalDate.of(2023, 4, 1)));
//        books.add(new Book(10, "978-3-16-148410-9", "Book Ten", "Author Ten", "Publisher Ten", 260, "Cookbook", "Description of Book Ten", LocalDate.of(2020, 12, 20)));
//
//        shops.add(new Shop(1, "123 Main St", "New York"));
//        shops.add(new Shop(2, "456 Elm St", "Los Angeles"));
//        shops.add(new Shop(3, "789 Oak St", "Chicago"));
//        shops.add(new Shop(4, "101 Pine St", "Houston"));
//        shops.add(new Shop(5, "121 Maple St", "Phoenix"));
//        shops.add(new Shop(6, "131 Cedar St", "Philadelphia"));
//        shops.add(new Shop(7, "141 Birch St", "San Antonio"));
//        shops.add(new Shop(8, "151 Spruce St", "San Diego"));
//        shops.add(new Shop(9, "161 Walnut St", "Dallas"));
//        shops.add(new Shop(10, "171 Sequoia St", "San Jose"));
//
//        warehouses.add(new Warehouse(1, "1 Warehouse St", "City 1"));
//        warehouses.add(new Warehouse(2, "2 Warehouse St", "City 2"));
//        warehouses.add(new Warehouse(3, "3 Warehouse St", "City 3"));
//        warehouses.add(new Warehouse(4, "4 Warehouse St", "City 4"));
//        warehouses.add(new Warehouse(5, "5 Warehouse St", "City 5"));
//        warehouses.add(new Warehouse(6, "6 Warehouse St", "City 6"));
//        warehouses.add(new Warehouse(7, "7 Warehouse St", "City 7"));
//        warehouses.add(new Warehouse(8, "8 Warehouse St", "City 8"));
//        warehouses.add(new Warehouse(9, "9 Warehouse St", "City 9"));
//        warehouses.add(new Warehouse(10, "10 Warehouse St", "City 10"));
//
//        for (int i = 1; i <= 10; i++) {
//            for (int j = 1; j <= 10; j++) {
//                WarehouseBook warehouseBook = WarehouseBook.builder()
//                        .storageId(i)
//                        .bookId(j)
//                        .bookAmount(i * 1000)
//                        .build();
//                warehouseBooks.add(warehouseBook);
//            }
//        }
//
//        for (int i = 1; i <= 10; i++) {
//            for(int j = 1; j <= 10; j++) {
//                ShopBook shopBook = ShopBook.builder()
//                        .storageId(i)
//                        .bookId(j)
//                        .bookAmount(i * 1000)
//                        .build();
//                shopBooks.add(shopBook);
//            }
//        }
//
//    }
//
//}
