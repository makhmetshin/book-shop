package ru.ifellow.jschool.machmetshin.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.ifellow.jschool.machmetshin.dto.user.CreateUserDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserAccountDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserOrdersDto;
import ru.ifellow.jschool.machmetshin.service.AuthorizationService;
import ru.ifellow.jschool.machmetshin.service.UserService;



@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;
    private final AuthorizationService authorizationService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOrdersDto> findById(@RequestParam int id, Authentication authentication ) {

        if(authorizationService.isAdminOrUserWorksWithHisProfile(id, authentication))
            return ResponseEntity.ok().body(userService.findByUserIdWithOrders(id));

        else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAccountDto> accountInformation(@RequestParam int id, Authentication authentication) {

        if(authorizationService.isAdminOrUserWorksWithHisProfile(id, authentication))
            return ResponseEntity.ok().body(userService.findByUserIdAccountDetails(id));

        else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PatchMapping(path = "/account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAccountDetails(@RequestParam int id,  @RequestBody UserAccountDto updateDto, Authentication authentication) {

        if(authorizationService.isAdminOrUserWorksWithHisProfile(id, authentication)) {

            userService.updateAccountDetails(id, updateDto);
            return ResponseEntity.ok().body("Account updated");
        }
        else return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Access denied. User information was not updated, you should update your account or to be an admin");

    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody CreateUserDto createUserDto, Authentication authentication) {

        if (createUserDto.getRole().equals("USER")) {
            userService.save(createUserDto);
            return ResponseEntity.ok().body("User registered successfully");
        }

        if( !( (createUserDto.getRole().equals("ADMIN") || createUserDto.getRole().equals("MANAGER")) &&
                authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")) )) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. If you want to register admin or manager you should be admin");
        }

        userService.save(createUserDto);
        return ResponseEntity.ok().body("User registered successfully");
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteUser(@RequestParam int id, Authentication authentication) {

        if(authorizationService.isAdminOrUserWorksWithHisProfile(id, authentication)) {
            userService.delete(id);
            return ResponseEntity.ok("User was deleted");
        }
        else return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Access denied. User was not deleted, you should delete your account or to be an admin");
    }

}
