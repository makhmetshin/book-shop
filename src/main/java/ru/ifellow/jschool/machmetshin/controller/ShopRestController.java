package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;
import ru.ifellow.jschool.machmetshin.service.ShopService;

import java.util.List;

@RestController
@RequestMapping("api/v1/shops")
@RequiredArgsConstructor
public class ShopRestController {

    private final ShopService shopService;

    @GetMapping(path="")
    public List<StorageDto> findAll() {
        return shopService.findAllDto();
    }

    @GetMapping(path="/{id}")
    public StorageDto findById(@PathVariable Integer id) {
        return shopService.findByIdDto(id);
    }

}
