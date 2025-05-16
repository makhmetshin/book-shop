package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.order.OrderDto;
import ru.ifellow.jschool.machmetshin.entity.user.Role;
import ru.ifellow.jschool.machmetshin.service.AuthorizationService;
import ru.ifellow.jschool.machmetshin.service.OrderService;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;
    private final AuthorizationService authorizationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> findById(@RequestParam Integer id, Authentication authentication) {

        if(authorizationService.userHasRoleManagerOrAdmin(authentication) ||
                authorizationService.authenticatedUserGotThisOrder(id, authentication)  ) {

            return ResponseEntity.ok(orderService.findByIdWithDependencies(id));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
