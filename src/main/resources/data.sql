
INSERT INTO STORAGE (address, city, storage_type) VALUES
    ('Lenina St, 1', 'Moscow', 'SHOP'),
    ('Mir Ave, 10', 'Saint Petersburg', 'SHOP'),
    ('Kirova St, 3', 'Kazan', 'SHOP'),
    ('Sadovaya St, 5', 'Novosibirsk', 'SHOP'),
    ('Pobedy St, 12', 'Yekaterinburg', 'SHOP'),
    ('Gogolya St, 7', 'Nizhny Novgorod', 'SHOP'),
    ('Pushkina St, 9', 'Rostov-on-Don', 'SHOP'),
    ('Chekhova St, 11', 'Samara', 'SHOP'),
    ('Tolstogo St, 13', 'Ufa', 'SHOP'),
    ('Dostoevskogo St, 15', 'Perm', 'SHOP'),

    ('Warehouse #1, Tsentralnaya St, 1', 'Moscow', 'WAREHOUSE'),
    ('Warehouse #2, Yuzhnaya St, 2', 'Saint Petersburg', 'WAREHOUSE'),
    ('Warehouse #3, Severnaya St, 3', 'Kazan', 'WAREHOUSE'),
    ('Warehouse #4, Vostochnaya St, 4', 'Novosibirsk', 'WAREHOUSE'),
    ('Warehouse #5, Zapadnaya St, 5', 'Yekaterinburg', 'WAREHOUSE'),
    ('Warehouse #6, Industrial St, 6', 'Nizhny Novgorod', 'WAREHOUSE'),
    ('Warehouse #7, Lesnaya St, 7', 'Rostov-on-Don', 'WAREHOUSE'),
    ('Warehouse #8, Parkovaya St, 8', 'Samara', 'WAREHOUSE'),
    ('Warehouse #9, Zelenaya St, 9', 'Ufa', 'WAREHOUSE'),
    ('Warehouse #10, Berezovaya St, 10', 'Perm', 'WAREHOUSE');

INSERT INTO USERS (username, password, name, surname, last_name, email, user_role) VALUES
    ('alexey', 'password1', 'Alexey', 'Smirnov', 'Ivanovich', 'alexey.smirnov@example.com', 'USER'),
    ('elena', 'password2', 'Elena', 'Ivanova', 'Petrovna', 'elena.ivanova@example.com', 'USER'),
    ('petr', 'password3', 'Petr', 'Vlasov', 'Nikolaevich', 'petr.vlasov@example.com', 'USER'),
    ('nikita', 'password4', 'Nikita', 'Morozov', 'Sergeevich', 'nikita.morozov@example.com', 'USER'),
    ('anastasia', 'password5', 'Anastasia', 'Belova', 'Vladimirovna', 'anastasia.belova@example.com', 'USER'),
    ('igor', 'password6', 'Igor', 'Lebedev', 'Alexandrovich', 'igor.lebedev@example.com', 'USER'),
    ('svetlana', 'password7', 'Svetlana', 'Pavlova', 'Igorevna', 'svetlana.pavlova@example.com', 'USER'),
    ('dmitry', 'password8', 'Dmitry', 'Orlov', 'Viktorovich', 'dmitry.orlov@example.com', 'USER'),
    ('yulia', 'password9', 'Yulia', 'Fomina', 'Igorevna', 'yulia.fomina@example.com', 'USER'),
    ('maxim', 'password10', 'Maxim', 'Kotov', 'Sergeevich', 'maxim.kotov@example.com', 'MANAGER');

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


INSERT INTO BOOK (id, ISBN, title, author_id, publisher_id, genre, published_date, pages_amount) VALUES
    (1, '978-1-2345-0001', 'Cosmos 3000', 1, 1, 'Science Fiction', '2020-01-01', 400),
    (2, '978-1-2345-0002', 'Love Forever', 1, 1, 'Romance', '2019-05-12', 400),
    (3, '978-1-2345-0003', 'The Baker Street Mystery', 1, 1, 'Detective', '2021-10-20', 400),
    (4, '978-1-2345-0004', 'The Physics of the Future', 2, 1, 'Science', '2018-03-14', 400),
    (5, '978-1-2345-0005', 'Dragon’s Shadow', 2, 2, 'Fantasy', '2020-12-12', 400),
    (6, '978-1-2345-0006', '1812. War and Peace', 2, 2, 'History', '1869-01-01', 400),
    (7, '978-1-2345-0007', 'Pirate Odyssey', 2, 2, 'Adventure', '2017-07-07', 400),
    (8, '978-1-2345-0008', 'Laughter and Sin', 2, 3, 'Humor', '2022-02-02', 400),
    (9, '978-1-2345-0009', 'You Are Your Brain', 3, 3, 'Psychology', '2021-11-11', 400),
    (10, '978-1-2345-0010', 'Urban Romance', 3, 3, 'Contemporary', '2023-04-04', 500);

-- Shops (storage_id 1-10), goods (good_id 1–10)
INSERT INTO storage_good (storage_id, good_id, quantity) VALUES
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

-- Warehouses (storage_id 11–20), goods (good_id 1–10)
INSERT INTO storage_good (storage_id, good_id, quantity) VALUES
    (11, 1, 1000), (11, 2, 1000), (11, 3, 1000), (11, 4, 1000), (11, 5, 1000),
    (11, 6, 1000), (11, 7, 1000), (11, 8, 1000), (11, 9, 1000), (11, 10, 1000),

    (12, 1, 1000), (12, 2, 1000), (12, 3, 1000), (12, 4, 1000), (12, 5, 1000),
    (12, 6, 1000), (12, 7, 1000), (12, 8, 1000), (12, 9, 1000), (12, 10, 1000),

    (13, 1, 1000), (13, 2, 1000), (13, 3, 1000), (13, 4, 1000), (13, 5, 1000),
    (13, 6, 1000), (13, 7, 1000), (13, 8, 1000), (13, 9, 1000), (13, 10, 1000),

    (14, 1, 1000), (14, 2, 1000), (14, 3, 1000), (14, 4, 1000), (14, 5, 1000),
    (14, 6, 1000), (14, 7, 1000), (14, 8, 1000), (14, 9, 1000), (14, 10, 1000),

    (15, 1, 1000), (15, 2, 1000), (15, 3, 1000), (15, 4, 1000), (15, 5, 1000),
    (15, 6, 1000), (15, 7, 1000), (15, 8, 1000), (15, 9, 1000), (15, 10, 1000),

    (16, 1, 1000), (16, 2, 1000), (16, 3, 1000), (16, 4, 1000), (16, 5, 1000),
    (16, 6, 1000), (16, 7, 1000), (16, 8, 1000), (16, 9, 1000), (16, 10, 1000),

    (17, 1, 1000), (17, 2, 1000), (17, 3, 1000), (17, 4, 1000), (17, 5, 1000),
    (17, 6, 1000), (17, 7, 1000), (17, 8, 1000), (17, 9, 1000), (17, 10, 1000),

    (18, 1, 1000), (18, 2, 1000), (18, 3, 1000), (18, 4, 1000), (18, 5, 1000),
    (18, 6, 1000), (18, 7, 1000), (18, 8, 1000), (18, 9, 1000), (18, 10, 1000),

    (19, 1, 1000), (19, 2, 1000), (19, 3, 1000), (19, 4, 1000), (19, 5, 1000),
    (19, 6, 1000), (19, 7, 1000), (19, 8, 1000), (19, 9, 1000), (19, 10, 1000),

    (20, 1, 1000), (20, 2, 1000), (20, 3, 1000), (20, 4, 1000), (20, 5, 1000),
    (20, 6, 1000), (20, 7, 1000), (20, 8, 1000), (20, 9, 1000), (20, 10, 1000);

INSERT INTO ORDERS (user_id, order_date, arrival_date, order_status, total_price, departure_warehouse_id, arrival_shop_id, web) VALUES
    (1, '2024-03-01', '2024-03-05', 'ASSEMBLING', 1050, 11, 1, true),
    (2, '2024-03-02', '2024-03-06', 'TRANSIT', 600, 11, 1, true),
    (3, '2024-03-03', '2024-03-07', 'READY', 450, 11, 1, true),
    (4, '2024-03-04', '2024-03-08', 'FINISHED', 1200, 11, 1, false),
    (5, '2024-03-05', '2024-03-09', 'READY', 700, 11, 1, true),
    (6, '2024-03-06', '2024-03-10', 'ASSEMBLING', 630, 11, 1, true),
    (7, '2024-03-07', '2024-03-11', 'TRANSIT', 480, 11, 1, true),
    (8, '2024-03-08', '2024-03-12', 'READY', 950, 11, 1, true),
    (9, '2024-03-09', '2024-03-13', 'FINISHED', 610, 11, 1, false),
    (10, '2024-03-10', '2024-03-14', 'ASSEMBLING', 560, 11, 1, true);

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

INSERT INTO BILL (order_id, shop_id, date, returned) VALUES
    (1, 1, '2024-03-06', false),
    (2, 1, '2024-03-07', false),
    (3, 1, '2024-03-08', false),
    (4, 1, '2024-03-09', false),
    (5, 1, '2024-03-10', false),
    (6, 1, '2024-03-11', false),
    (7, 1, '2024-03-12', false),
    (8, 1, '2024-03-13', false),
    (9, 1, '2024-03-14', false),
    (10, 1,'2024-03-15', false);