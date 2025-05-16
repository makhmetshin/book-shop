package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodGetAmountDto;
import ru.ifellow.jschool.machmetshin.service.StorageGoodService;
import ru.ifellow.jschool.machmetshin.service.WarehouseService;

@RestController
@RequestMapping("api/v1/storages")
@RequiredArgsConstructor
public class StorageGoodRestController {

    private final StorageGoodService storageGoodService;

    @PostMapping
    public Integer getAmountOfGood(@RequestBody StorageGoodGetAmountDto storageGoodGetAmountDto) {
        return storageGoodService.getAmountOfGood(storageGoodGetAmountDto);
    }
}
