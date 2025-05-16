package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.good.book.BookDto;
import ru.ifellow.jschool.machmetshin.dto.order.BillDto;
import ru.ifellow.jschool.machmetshin.dto.order.CreateOrderDto;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.DistributeGoodDto;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.FindBooksDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;
import ru.ifellow.jschool.machmetshin.dto.user.CreateUserDto;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.service.StorageGoodService;
import ru.ifellow.jschool.machmetshin.service.UserService;
import ru.ifellow.jschool.machmetshin.service.manager.ShopManagerService;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1/shops")
public class ShopManagerRestController {
    private final ShopManagerService shopManagerService;

    @PostMapping(path = "/sell", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BillDto sellGoods(@RequestBody CreateOrderDto createOrderDto) {
        return shopManagerService.sellGoods(createOrderDto);
    }

    @PatchMapping(path = "/distribute", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String distributeGoods(@RequestBody DistributeGoodDto distributeGoodDto) {
        shopManagerService.distributeGood(distributeGoodDto);
        return "Goods were distributed";
    }

    @PatchMapping(path = "/return")
    public String returnGoods (@RequestParam Integer billId) {
        shopManagerService.returnGoods(billId);
        return "Goods have been returned";
    }

    @PostMapping(path = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> books(@RequestBody FindBooksDto findBooksDto, @RequestParam Integer shopId) {

        return shopManagerService.findBooksInShopByGenreAndAuthor(shopId, findBooksDto);
    }

    @GetMapping
    public List<StorageDto> findAll() {
        return shopManagerService.findAllShops();
    }

    @GetMapping(path="/{id}")
    public StorageDto findById(@PathVariable Integer id) {
        return shopManagerService.findShopById(id);
    }

}
