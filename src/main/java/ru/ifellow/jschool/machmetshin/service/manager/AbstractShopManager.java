package ru.ifellow.jschool.machmetshin.service.manager;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.dto.order.CreateOrderDto;
import ru.ifellow.jschool.machmetshin.dto.order.CreateWebOrderDto;
import ru.ifellow.jschool.machmetshin.dto.order.OrderItemDtoWithGoodId;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodGetAmountDto;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;
import ru.ifellow.jschool.machmetshin.entity.storage.*;
import ru.ifellow.jschool.machmetshin.entity.user.User;
import ru.ifellow.jschool.machmetshin.service.*;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@NoArgsConstructor(force = true)
@AllArgsConstructor
public abstract class AbstractShopManager {

    private final StorageGoodService storageGoodService;
    private final OrderService orderService;
    private final WarehouseService warehouseService;
    private final ShopService shopService;
    private final UserService userService;
    private final GoodService goodService;
    private final EntityExistsValidator entityExistsValidator;
    private final BillService billService;

    protected Order createOrder(CreateOrderDto createOrderDto, Boolean webOrder) {
        Integer userId = createOrderDto.getUserId();
        Integer receiveStorageId = createOrderDto.getReceiveStorageId();
        List<OrderItemDtoWithGoodId> orderItemDtoWithGoodDtos = createOrderDto.getOrderItemDtosWithGoodId();

        Shop arrivalShop = entityExistsValidator.validate(shopService.findById(receiveStorageId), receiveStorageId, Shop.class);
        int totalPrice = 0;
        LocalDate today = LocalDate.now();

        Integer goodSupplierStorageId;
        StorageType storageType;
        Warehouse warehouse = null;
        if (webOrder) {
            storageType = StorageType.WAREHOUSE;
            goodSupplierStorageId =((CreateWebOrderDto) createOrderDto).getWarehouseId();
            warehouse = entityExistsValidator.validate(warehouseService.findById(goodSupplierStorageId), goodSupplierStorageId, Warehouse.class);
        }
        else {
            goodSupplierStorageId = receiveStorageId;
            storageType = StorageType.SHOP;
        }

        for( OrderItemDtoWithGoodId orderItemDtoWithGoodDto : orderItemDtoWithGoodDtos) {
            Integer goodId = orderItemDtoWithGoodDto.getGoodId();
            Integer amount = orderItemDtoWithGoodDto.getQuantity();

            if (storageGoodService.getAmountOfGood(new StorageGoodGetAmountDto(goodId, goodSupplierStorageId, storageType)) < amount )
                throw new IllegalStateException("not enough goods with id %d in the storage with id %d".formatted(goodId, goodSupplierStorageId));

            storageGoodService.removeGood(new StorageGoodDto(goodId, goodSupplierStorageId, amount));
            totalPrice += orderItemDtoWithGoodDto.getPriceAtPurchase() * amount;
        }

        Set<OrderItem> orderItems = orderItemDtoWithGoodDtos.stream()
                .map(dto -> OrderItem.builder()
                        .good(entityExistsValidator.validate(goodService.findById(dto.getGoodId()), dto.getGoodId(), Good.class ))
                        .quantity(dto.getQuantity())
                        .priceAtPurchase(dto.getPriceAtPurchase())
                        .build())
                .collect(Collectors.toSet());

        User user = entityExistsValidator.validate(userService.findById(userId), userId, User.class);

        Order order = Order.builder()
                .user(user)
                .orderItems(orderItems.stream().collect(Collectors.toSet()) )
                .orderDate(today)
                .orderStatus(OrderStatus.FINISHED)
                .totalPrice(totalPrice)
                .arrivalShop(arrivalShop)
                .web(false)
                .build();

        if (webOrder) {
            order.setOrderStatus(OrderStatus.ASSEMBLING);
            order.setArrivalDate(today.plusDays(5));
            order.setDepartureWarehouse(warehouse);
            order.setWeb(true);
        }

        orderService.save(order);

        return order;
    }

    @Transactional
    public void returnGoods(Integer billId) {
        Bill bill = entityExistsValidator.validate(billService.findById(billId), billId, Bill.class);
        if (bill.getReturned() == true)
            throw new IllegalStateException("Order with this bill is already returned");
        Shop shop = bill.getShop();
        Order order = bill.getOrder();
        order.setOrderStatus(OrderStatus.CANCELLED);
        bill.setReturned(true);

        order.getOrderItems().stream()
                .forEach(it -> storageGoodService.addGood(new StorageGoodDto(it.getGood().getId(), shop.getId(), it.getQuantity())));

    }


    public Optional<StorageGood> findStorageGoodById(Integer goodId, Integer storageId) {
        return storageGoodService.findByStorageIdAndGoodId(storageId, goodId);
    }

    public List<StorageDto> findAllShops() {
        return shopService.findAllDto();
    }

    public StorageDto findShopById(Integer shopId) {
        return shopService.findByIdDto(shopId);
    }

}
