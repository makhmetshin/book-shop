package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
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
        // чтобы посмотреть что информация юзера была изменена
        // можно воспользоваться get запросом на юзера с его id
        return "Account updated";
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser(@RequestBody CreateUserDto createUserDto) {
        userService.save(createUserDto);
        // чтобы посмотреть что юзер был добавлен можно воспользоваться get запросом на
        // юзера с id = 11, так как до этого было 10
        return "User created";
    }

    @DeleteMapping(path = "/delete")
    public String deleteUser(@RequestParam int id) {
        userService.delete(id);
        // чтобы посмотреть что юзер удален можно воспользоваться get запросом на юзера с его id
        return "User deleted";
    }

    @GetMapping("/test")
    public String test(Authentication authentication) {

        System.out.println(authentication);
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("AUTH ARGUMENT: " + authentication);
        System.out.println("SECURITY CONTEXT AUTH: " + context.getAuthentication());

        return "Current user: " + (authentication != null ? authentication.getName() : "none");
    }


}
