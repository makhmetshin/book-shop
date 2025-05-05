package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.good.book.BookDto;
import ru.ifellow.jschool.machmetshin.dto.order.CreateWebOrderDto;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.FindBooksDto;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;
import ru.ifellow.jschool.machmetshin.service.OrderService;
import ru.ifellow.jschool.machmetshin.service.StorageGoodService;
import ru.ifellow.jschool.machmetshin.service.manager.WebShopManagerService;

import java.util.List;

@RequestMapping("/api/v1/web_shop")
@RestController()
@RequiredArgsConstructor
public class WebShopManagerRestController {

    private final WebShopManagerService webShopManagerService;
    private final OrderService orderService;
    private final StorageGoodService storageGoodService;


    @PostMapping(path = "create_order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createOrder(@RequestBody CreateWebOrderDto createWebOrderDto) {
        webShopManagerService.createOrder(createWebOrderDto);
        return "order created";
    }

    @PatchMapping(path = "cancel_order")
    public String cancelOrder(@RequestParam Integer orderId) {
        webShopManagerService.cancelOrder(orderId);
        return "order with id %d canceled".formatted(orderId);
    }


    @PatchMapping(path = "change_order_status")
    public String changeOrderStatus(@RequestParam Integer orderId, @RequestParam OrderStatus status) {
        webShopManagerService.changeOrderStatus(status,orderId);
        System.out.println(orderService.findById(orderId));
        return "order with id %d changed its status to %s".formatted(orderId, status.name());
    }
    @PatchMapping(path = "takeaway_order")
    public String takeawayOrder(@RequestParam Integer orderId) {
        webShopManagerService.takeawayOrder(orderId);
        System.out.println(orderService.findById(orderId));
        return "order with id %d was taken by the client".formatted(orderId);
    }

    @PatchMapping(path = "/return")
    public String sellGoods(@RequestParam Integer billId) {
        webShopManagerService.returnGoods(billId);
        System.out.println(storageGoodService.findByStorageIdAndGoodId(1, 1));
        System.out.println(storageGoodService.findByStorageIdAndGoodId(1, 3));
        return "Goods have been returned";
    }

    @PostMapping(path = "books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> findBooks(@RequestBody FindBooksDto findBooksDto) {
        List<BookDto> bookDtos = webShopManagerService.findBooksByGenreAndAuthor(findBooksDto);
        System.out.println(bookDtos);
        return bookDtos;
    }
}
