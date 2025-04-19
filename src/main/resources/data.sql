
INSERT INTO SHOP (address, city) VALUES
    ('Lenina St, 1', 'Moscow'),
    ('Mir Ave, 10', 'Saint Petersburg'),
    ('Kirova St, 3', 'Kazan'),
    ('Sadovaya St, 5', 'Novosibirsk'),
    ('Pobedy St, 12', 'Yekaterinburg'),
    ('Gogolya St, 7', 'Nizhny Novgorod'),
    ('Pushkina St, 9', 'Rostov-on-Don'),
    ('Chekhova St, 11', 'Samara'),
    ('Tolstogo St, 13', 'Ufa'),
    ('Dostoevskogo St, 15', 'Perm');

INSERT INTO WAREHOUSE (address, city) VALUES
    ('Warehouse #1, Tsentralnaya St, 1', 'Moscow'),
    ('Warehouse #2, Yuzhnaya St, 2', 'Saint Petersburg'),
    ('Warehouse #3, Severnaya St, 3', 'Kazan'),
    ('Warehouse #4, Vostochnaya St, 4', 'Novosibirsk'),
    ('Warehouse #5, Zapadnaya St, 5', 'Yekaterinburg'),
    ('Warehouse #6, Industrial St, 6', 'Nizhny Novgorod'),
    ('Warehouse #7, Lesnaya St, 7', 'Rostov-on-Don'),
    ('Warehouse #8, Parkovaya St, 8', 'Samara'),
    ('Warehouse #9, Zelenaya St, 9', 'Ufa'),
    ('Warehouse #10, Berezovaya St, 10', 'Perm');

INSERT INTO USERS (username, password, name, surname, last_name, email) VALUES
    ('alexey', 'password1', 'Alexey', 'Smirnov', 'Ivanovich', 'alexey.smirnov@example.com'),
    ('elena', 'password2', 'Elena', 'Ivanova', 'Petrovna', 'elena.ivanova@example.com'),
    ('petr', 'password3', 'Petr', 'Vlasov', 'Nikolaevich', 'petr.vlasov@example.com'),
    ('nikita', 'password4', 'Nikita', 'Morozov', 'Sergeevich', 'nikita.morozov@example.com'),
    ('anastasia', 'password5', 'Anastasia', 'Belova', 'Vladimirovna', 'anastasia.belova@example.com'),
    ('igor', 'password6', 'Igor', 'Lebedev', 'Alexandrovich', 'igor.lebedev@example.com'),
    ('svetlana', 'password7', 'Svetlana', 'Pavlova', 'Igorevna', 'svetlana.pavlova@example.com'),
    ('dmitry', 'password8', 'Dmitry', 'Orlov', 'Viktorovich', 'dmitry.orlov@example.com'),
    ('yulia', 'password9', 'Yulia', 'Fomina', 'Igorevna', 'yulia.fomina@example.com'),
    ('maxim', 'password10', 'Maxim', 'Kotov', 'Sergeevich', 'maxim.kotov@example.com');

INSERT INTO GOOD (good_type, price, description) VALUES
    ('BOOK', 500, 'Science fiction about space'),
    ('BOOK', 600, 'Romance novel'),
    ('BOOK', 450, 'Detective in London'),
    ('BOOK', 550, 'Scientific book'),
    ('BOOK', 480, 'Fantasy epic'),
    ('BOOK', 700, 'Historical novel'),
    ('BOOK', 520, 'Pirate adventures'),
    ('BOOK', 630, 'Humor and satire'),
    ('BOOK', 560, 'Psychology for everyone'),
    ('BOOK', 610, 'Contemporary novel');


INSERT INTO AUTHOR (first_name, last_name, birth_date) VALUES
    ('Leo', 'Tolstoy', '1828-09-09'),
    ('Fyodor', 'Dostoevsky', '1821-11-11'),
    ('Anton', 'Chekhov', '1860-01-29');

INSERT INTO PUBLISHER (name, address, city) VALUES
    ('Penguin Books', '80 Strand', 'London'),
    ('HarperCollins', '195 Broadway', 'New York'),
    ('Vintage Books', '120 Riverside Blvd', 'New York');


INSERT INTO BOOK (id, ISBN, title, author_id, publisher_id, genre, published_date) VALUES
    (1, '978-1-2345-0001', 'Cosmos 3000', 1, 1, 'Science Fiction', '2020-01-01'),
    (2, '978-1-2345-0002', 'Love Forever', 1, 1, 'Romance', '2019-05-12'),
    (3, '978-1-2345-0003', 'The Baker Street Mystery', 1, 1, 'Detective', '2021-10-20'),
    (4, '978-1-2345-0004', 'The Physics of the Future', 2, 1, 'Science', '2018-03-14'),
    (5, '978-1-2345-0005', 'Dragon’s Shadow', 2, 2, 'Fantasy', '2020-12-12'),
    (6, '978-1-2345-0006', '1812. War and Peace', 2, 2, 'History', '1869-01-01'),
    (7, '978-1-2345-0007', 'Pirate Odyssey', 2, 2, 'Adventure', '2017-07-07'),
    (8, '978-1-2345-0008', 'Laughter and Sin', 2, 3, 'Humor', '2022-02-02'),
    (9, '978-1-2345-0009', 'You Are Your Brain', 3, 3, 'Psychology', '2021-11-11'),
    (10, '978-1-2345-0010', 'Urban Romance', 3, 3, 'Contemporary', '2023-04-04');

INSERT INTO SHOP_BOOK ( shop_id, book_id, book_Amount)
VALUES
    (1, 1, 1000), (1, 2, 1000), (1, 3, 1000), (1, 4, 1000), (1, 5, 1000),
    (1, 6, 1000), (1, 7, 1000), (1, 8, 1000), (1, 9, 1000), (1, 10, 1000),

    (2, 1, 1000), (2, 2, 1000), (2, 3, 1000), (2, 4, 1000), (2, 5, 1000),
    (2, 6, 1000), (2, 7, 1000), (2, 8, 1000), (2, 9, 1000), (2, 10, 1000),

    (3, 1, 1000), (3, 2, 1000), (3, 3, 1000), (3, 4, 1000), (3, 5, 1000),
    (3, 6, 1000), (3, 7, 1000), (3, 8, 1000), (3, 9, 1000), (3, 10, 1000),

    (4, 1, 1000), (4, 2, 1000), (4, 3, 1000), (4, 4, 1000), (4, 5, 1000),
    (4, 6, 1000), (4, 7, 1000), (4, 8, 1000), (4, 9, 1000), (4, 10, 1000),

    (5, 1, 1000), (5, 2, 1000), (5, 3, 1000), (5, 4, 1000), (5, 5, 1000),
    (5, 6, 1000), (5, 7, 1000), (5, 8, 1000), (5, 9, 1000), (5, 10, 1000),

    (6, 1, 1000), (6, 2, 1000), (6, 3, 1000), (6, 4, 1000), (6, 5, 1000),
    (6, 6, 1000), (6, 7, 1000), (6, 8, 1000), (6, 9, 1000), (6, 10, 1000),

    (7, 1, 1000), (7, 2, 1000), (7, 3, 1000), (7, 4, 1000), (7, 5, 1000),
    (7, 6, 1000), (7, 7, 1000), (7, 8, 1000), (7, 9, 1000), (7, 10, 1000),

    (8, 1, 1000), (8, 2, 1000), (8, 3, 1000), (8, 4, 1000), (8, 5, 1000),
    (8, 6, 1000), (8, 7, 1000), (8, 8, 1000), (8, 9, 1000), (8, 10, 1000),

    (9, 1, 1000), (9, 2, 1000), (9, 3, 1000), (9, 4, 1000), (9, 5, 1000),
    (9, 6, 1000), (9, 7, 1000), (9, 8, 1000), (9, 9, 1000), (9, 10, 1000),

    (10, 1, 1000), (10, 2, 1000), (10, 3, 1000), (10, 4, 1000), (10, 5, 1000),
    (10, 6, 1000), (10, 7, 1000), (10, 8, 1000), (10, 9, 1000), (10, 10, 1000);

INSERT INTO WAREHOUSE_BOOK (warehouse_id, book_id, book_amount)
VALUES
    (1, 1, 1000), (1, 2, 1000), (1, 3, 1000), (1, 4, 1000), (1, 5, 1000),
    (1, 6, 1000), (1, 7, 1000), (1, 8, 1000), (1, 9, 1000), (1, 10, 1000),

    (2, 1, 1000), (2, 2, 1000), (2, 3, 1000), (2, 4, 1000), (2, 5, 1000),
    (2, 6, 1000), (2, 7, 1000), (2, 8, 1000), (2, 9, 1000), (2, 10, 1000),

    (3, 1, 1000), (3, 2, 1000), (3, 3, 1000), (3, 4, 1000), (3, 5, 1000),
    (3, 6, 1000), (3, 7, 1000), (3, 8, 1000), (3, 9, 1000), (3, 10, 1000),

    (4, 1, 1000), (4, 2, 1000), (4, 3, 1000), (4, 4, 1000), (4, 5, 1000),
    (4, 6, 1000), (4, 7, 1000), (4, 8, 1000), (4, 9, 1000), (4, 10, 1000),

    (5, 1, 1000), (5, 2, 1000), (5, 3, 1000), (5, 4, 1000), (5, 5, 1000),
    (5, 6, 1000), (5, 7, 1000), (5, 8, 1000), (5, 9, 1000), (5, 10, 1000),

    (6, 1, 1000), (6, 2, 1000), (6, 3, 1000), (6, 4, 1000), (6, 5, 1000),
    (6, 6, 1000), (6, 7, 1000), (6, 8, 1000), (6, 9, 1000), (6, 10, 1000),

    (7, 1, 1000), (7, 2, 1000), (7, 3, 1000), (7, 4, 1000), (7, 5, 1000),
    (7, 6, 1000), (7, 7, 1000), (7, 8, 1000), (7, 9, 1000), (7, 10, 1000),

    (8, 1, 1000), (8, 2, 1000), (8, 3, 1000), (8, 4, 1000), (8, 5, 1000),
    (8, 6, 1000), (8, 7, 1000), (8, 8, 1000), (8, 9, 1000), (8, 10, 1000),

    (9, 1, 1000), (9, 2, 1000), (9, 3, 1000), (9, 4, 1000), (9, 5, 1000),
    (9, 6, 1000), (9, 7, 1000), (9, 8, 1000), (9, 9, 1000), (9, 10, 1000),

    (10, 1, 1000), (10, 2, 1000), (10, 3, 1000), (10, 4, 1000), (10, 5, 1000),
    (10, 6, 1000), (10, 7, 1000), (10, 8, 1000), (10, 9, 1000), (10, 10, 1000);

INSERT INTO ORDERS (user_id, order_date, arrival_date, status, total_price, departure_warehouse_id, arrival_shop_id) VALUES
    (1, '2024-03-01', '2024-03-05', 'ASSEMBLING', 1050, 1, 1),
    (2, '2024-03-02', '2024-03-06', 'TRANSIT', 600, 1, 1),
    (3, '2024-03-03', '2024-03-07', 'READY', 450, 1, 1),
    (4, '2024-03-04', '2024-03-08', 'FINISHED', 1200, 1, 1),
    (5, '2024-03-05', '2024-03-09', 'READY', 700, 1, 1),
    (6, '2024-03-06', '2024-03-10', 'ASSEMBLING', 630, 1, 1),
    (7, '2024-03-07', '2024-03-11', 'TRANSIT', 480, 1, 1),
    (8, '2024-03-08', '2024-03-12', 'READY', 950, 1, 1),
    (9, '2024-03-09', '2024-03-13', 'FINISHED', 610, 1, 1),
    (10, '2024-03-10', '2024-03-14', 'ASSEMBLING', 560, 1, 1);

INSERT INTO ORDER_ITEM (order_id, good_id, quantity, price_at_purchase) VALUES
    (1, 1, 1, 500),
    (1, 3, 1, 450),
    (2, 2, 1, 600),
    (3, 3, 1, 450),
    (4, 4, 2, 600),
    (5, 6, 1, 700),
    (6, 8, 1, 630),
    (7, 5, 1, 480),
    (8, 7, 1, 520),
    (8, 9, 1, 430),
    (9, 10, 1, 610),
    (10, 9, 1, 560);

INSERT INTO BILL (order_id, shop_id, date) VALUES
    (1, 1, '2024-03-06'),
    (2, 1, '2024-03-07'),
    (3, 1, '2024-03-08'),
    (4, 1, '2024-03-09'),
    (5, 1, '2024-03-10'),
    (6, 1, '2024-03-11'),
    (7, 1, '2024-03-12'),
    (8, 1, '2024-03-13'),
    (9, 1, '2024-03-14'),
    (10, 1,'2024-03-15');