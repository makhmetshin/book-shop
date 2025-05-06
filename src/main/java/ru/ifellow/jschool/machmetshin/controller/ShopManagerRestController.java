package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.good.book.BookDto;
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
    private final StorageGoodService storageGoodService;

    @PostMapping(path = "/sell", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String sellGoods(@RequestBody CreateOrderDto createOrderDto) {
        System.out.println(createOrderDto);
        Bill bill =  shopManagerService.sellGoods(createOrderDto);
        System.out.println(bill.getOrder());
        System.out.println(storageGoodService.findByStorageIdAndGoodId(1,1));
        System.out.println(storageGoodService.findByStorageIdAndGoodId(1,2));
        return "Goods were sold";
    }

    @PatchMapping(path = "/distribute", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String distributeGoods(@RequestBody DistributeGoodDto distributeGoodDto) {
        System.out.println(distributeGoodDto);
        shopManagerService.distributeGood(distributeGoodDto);
        System.out.println(storageGoodService.findByStorageIdAndGoodId(1, 1));
        System.out.println(storageGoodService.findByStorageIdAndGoodId(4, 1));
        System.out.println(storageGoodService.findByStorageIdAndGoodId(11, 1));
        return "Goods were distributed";
    }

    @PatchMapping(path = "/return")
    public String returnGoods (@RequestParam Integer billId) {
        shopManagerService.returnGoods(billId);
        System.out.println(storageGoodService.findByStorageIdAndGoodId(1, 1));
        System.out.println(storageGoodService.findByStorageIdAndGoodId(1, 3));
        return "Goods have been returned";
    }

    @PostMapping(path = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> books(@RequestBody FindBooksDto findBooksDto, @RequestParam Integer shopId) {
        System.out.println(shopManagerService.findBooksInShopByGenreAndAuthor(shopId, findBooksDto));
        return shopManagerService.findBooksInShopByGenreAndAuthor(shopId, findBooksDto);
    }

    @GetMapping(path="")
    public List<StorageDto> findAll() {
        return shopManagerService.findAllShops();
    }

    @GetMapping(path="/{id}")
    public StorageDto findById(@PathVariable Integer id) {
        return shopManagerService.findShopById(id);
    }

}
