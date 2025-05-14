package ru.ifellow.jschool.machmetshin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.entity.user.User;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // readOnly = true - хорошая практика, поддерживаю
public class AuthorizationService {

    private final UserService userService;

    public boolean isAdminOrUserWorksWithHisResources(Integer userIdWhichResourcesAreAffected, Authentication authentication) {
        User authenticatedUser = userService.findByUserName(authentication.getName());

        //можно сделать просто return authenticatedUser.getId().equals(userIdWhichResourcesAreAffected) || isAdmin(authentication)
        if(authenticatedUser.getId().equals(userIdWhichResourcesAreAffected) || isAdmin(authentication))
            return true;
        else return false;
    }

    public boolean authenticatedUserGotThisOrder(Integer orderId, Authentication authentication) {

        return userService.findByUserName(authentication.getName()).getOrders().stream()
                .map(order -> order.getId())
                .toList().contains(orderId);
    }

    public boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")) ;
    }
}
