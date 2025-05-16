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
import ru.ifellow.jschool.machmetshin.service.interfaces.Finder;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService implements Finder<Integer, Order> {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private EntityExistsValidator entityExistsValidator;

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
        Order order = entityExistsValidator.validate(orderRepository.findById(id), id, Order.class);

        List<OrderItemDtoWithGoodDto> orderItemDtosWithGood = order.getOrderItems().stream()
                .map(orderItem -> {
                    Good good = orderItem.getGood();
                    GoodDto goodDto = new GoodDto(good);
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
                .departureWarehouseDto(new StorageDto(warehouse))
                .arrivalShopDto(new StorageDto(shop))
                .userId(order.getUser().getId())
                .build();
    }

    public Set<Order> findByUserId(Integer userId) {
        return orderRepository.findByUserId(userId);
    }

}
