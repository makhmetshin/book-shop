package ru.ifellow.jschool.machmetshin.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.good.book.BookDto;
import ru.ifellow.jschool.machmetshin.dto.order.CreateWebOrderDto;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.FindBooksDto;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;
import ru.ifellow.jschool.machmetshin.entity.user.Role;
import ru.ifellow.jschool.machmetshin.service.AuthorizationService;
import ru.ifellow.jschool.machmetshin.service.OrderService;
import ru.ifellow.jschool.machmetshin.service.StorageGoodService;
import ru.ifellow.jschool.machmetshin.service.UserService;
import ru.ifellow.jschool.machmetshin.service.manager.WebShopManagerService;

import java.util.List;

@RequestMapping("/api/v1/web_shop")
@RestController()
@RequiredArgsConstructor
public class WebShopManagerRestController {

    private final WebShopManagerService webShopManagerService;
    private final AuthorizationService authorizationService;


    @PostMapping(path = "create_order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrder(@RequestBody CreateWebOrderDto createWebOrderDto, Authentication authentication) {

        if(authorizationService.isAdminOrUserWorksWithHisProfile(createWebOrderDto.getUserId(), authentication)) {

            webShopManagerService.createOrder(createWebOrderDto);
            return ResponseEntity.ok().body("Order was successfully created");
        }
        else return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Access denied. You can create order only for your account. Only admin can create orders for other accounts");
    }

    @PatchMapping(path = "/cancel_order")
    public ResponseEntity<String> cancelOrder(@RequestParam Integer orderId, Authentication authentication) {

        if( authorizationService.authenticatedUserGotThisOrder(orderId, authentication)
                || authorizationService.userHasRole(authentication, Role.ADMIN)) {
            try {
                webShopManagerService.cancelOrder(orderId);
            }
            catch (EntityNotFoundException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
            return ResponseEntity.ok().body("Order was successfully canceled");
        }
        else return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Access denied. You can cancel order only for your account. Only admin can cancel orders for other accounts");
    }


    @PatchMapping(path = "/change_order_status")
    public String changeOrderStatus(@RequestParam Integer orderId, @RequestParam OrderStatus status) {
        webShopManagerService.changeOrderStatus(status,orderId);
        return "order with id %d changed its status to %s".formatted(orderId, status.name());
    }
    @PatchMapping(path = "/takeaway_order")
    public String takeawayOrder(@RequestParam Integer orderId) {
        webShopManagerService.takeawayOrder(orderId);
        return "order with id %d was taken by the client".formatted(orderId);
    }

    @PatchMapping(path = "/return")
    public String returnGoods(@RequestParam Integer billId) {
        webShopManagerService.returnGoods(billId);
        return "Goods have been returned";
    }

    @PostMapping(path = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> findBooks(@RequestBody FindBooksDto findBooksDto) {
        List<BookDto> bookDtos = webShopManagerService.findBooksByGenreAndAuthor(findBooksDto);
        return bookDtos;
    }
}
