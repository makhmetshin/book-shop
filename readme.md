# Книжный магазин
Стек технологий:  
Java, Spring Framework (Core, Web, Data,  Security, Scheduler),  Hibernate, JUnit 5, Mockito, AssertJ, Gradle, H2, Lombok, Tomcat 
## Назначение:  
Управление магазинами,онлайн-магазином, складами, чеками, заказами, товарами  
Работа с пользователями  

## API
### Управление заказами
/api/v1/orders?id="orderID" - получение информации по заказу  

### Управление физическими магазинами  
/api/v1/shops/sell - продажа товаров и выписка чека  
/api/v1/shops/distribute - распределение товара по нескольким магазинам с определенного склада  
/api/v1/shops/return?billId="UUID" - возвращаение купленных товаров по чеку  
/api/v1/shops/books - поиск по книгам в наличии в определенном магазине по жанру названию автору  
/api/v1/shops/ - получение всех доступных магазинов  
/api/v1/shops/"shopId" - получение магазина по его ID  

### Работа с пользователями  
/api/v1/users?id="userId" - Получение закказов пользователя  
GET /api/v1/users/account - Получение информации об аккаунте  
PATCH /api/v1/users/account - Обновление информации об аккаунте  
/api/v1/users/register - Создание аккаунта  
/api/v1/users/delete - Удаление аккаунта  

### Управление онлайн магазином  
/api/v1/web_shop/create_order - Создать заказ  
/api/v1/web_shop/cancel_order?orderId="orderId" - Отменить заказ  
/api/v1/web_shop/change_order_status?orderId="orderId" - Изменить статус заказа  
/api/v1/web_shop/takeaway_order?orderId="orderId" - Забрать заказ из пункта выдачи и создать чек
/api/v1/web_shop/return?billId="billId" - Вернуть товары по чеку  
/api/v1/web_shop/books - Получить список доступных книг по названию, жанру, автору 


### Хеддеры для авторизации
 - "Authorization" = "Basic bWF4aW06cGFzc3dvcmQxMA==" - для менеджера maxim (maxim:password10)
 - "Authorization" = "Basic eXVsaWE6cGFzc3dvcmQ5" - для админа yulia (yulia:password9)
 - "Authorization" = "Basic ZG1pdHJ5OnBhc3N3b3JkOA==" - для обычного пользователя dmitry (dmitry:password8)

powerShell curl команды для ручного проведения запросов находятся в файле curls.txt
