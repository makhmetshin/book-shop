package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.OrderRepository;
import ru.ifellow.jschool.machmetshin.dto.good.GoodDto;
import ru.ifellow.jschool.machmetshin.dto.order.OrderDto;
import ru.ifellow.jschool.machmetshin.dto.order.OrderItemDtoWithGoodDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;
import ru.ifellow.jschool.machmetshin.validator.EntityFoundByIdRepositoryValidator;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService implements Findable<Integer, Order> {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final EntityFoundByIdRepositoryValidator entityFoundByIdRepositoryValidator;

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public OrderDto findByIdWithDependencies(Integer id) {
        Order order = entityFoundByIdRepositoryValidator.validate(orderRepository, id, Order.class);

        List<OrderItemDtoWithGoodDto> orderItemDtosWithGood = order.getOrderItems().stream()
                .map(orderItem -> {
                    Good good = orderItem.getGood();
                    GoodDto goodDto = new GoodDto(good.getId(), good.getGoodType(), good.getPrice(), good.getDescription());
                    return new OrderItemDtoWithGoodDto(goodDto, orderItem.getQuantity(), orderItem.getPriceAtPurchase());
                })
                .toList();
        Shop shop = order.getArrivalShop();
        Warehouse warehouse = order.getDepartureWarehouse();
        return OrderDto.builder()
                .id(order.getId())
                .orderItemDtoWithGoodDtos(orderItemDtosWithGood)
                .orderDate(order.getOrderDate())
                .arrivalDate(order.getArrivalDate())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .departureWarehouseDto(
                        new StorageDto(warehouse.getId(), warehouse.getAddress(), warehouse.getCity(), warehouse.getStorageType()))
                .arrivalShopDto(
                        new StorageDto(shop.getId(), shop.getAddress(), shop.getCity(), shop.getStorageType()))
                .user_id(order.getUser().getId())
                .build();
    }
}
