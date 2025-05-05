package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.user.CreateUserDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserAccountDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserOrdersDto;
import ru.ifellow.jschool.machmetshin.service.UserService;


@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserOrdersDto findById(@RequestParam int id ) {
        UserOrdersDto userOrdersDto =  userService.findByUserIdWithOrders(id);
        return userOrdersDto;
    }

    @GetMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAccountDto accountInformation(@RequestParam int id) {
         return userService.findByUserIdAccountDetails(id);
    }
    @PatchMapping(path = "/account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String accountUpdateAccountDetails(@RequestParam int id,  @RequestBody UserAccountDto updateDto) {
        userService.updateAccountDetails(id, updateDto);
        return "Account updated";
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser(@RequestBody CreateUserDto createUserDto) {
        userService.save(createUserDto);
        return "User created";
    }

    @DeleteMapping(path = "/delete")
    public String registerUser(@RequestParam int id) {
        userService.delete(id);
        return "User deleted";
    }


}
