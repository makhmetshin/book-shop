package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.order.OrderDto;
import ru.ifellow.jschool.machmetshin.service.OrderService;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto findById(@RequestParam Integer id) {
        // Не надо дважды вызывать метод сервиса, только для того, чтобы вывести результат в лог.
        // Можно же положить результат в локальную переменную =)
        System.out.println(orderService.findByIdWithDependencies(id));
        return orderService.findByIdWithDependencies(id);
    }
}
