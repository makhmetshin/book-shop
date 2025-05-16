package ru.ifellow.jschool.machmetshin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.entity.user.Role;
import ru.ifellow.jschool.machmetshin.entity.user.User;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorizationService {

    private final UserService userService;

    public boolean isAdminOrUserWorksWithHisProfile(Integer userIdWhichResourcesAreAffected, Authentication authentication) {
        User authenticatedUser = userService.findByUserName(authentication.getName());

        return authenticatedUser.getId().equals(userIdWhichResourcesAreAffected) || userHasRole(authentication, Role.ADMIN);
    }

    public boolean authenticatedUserGotThisOrder(Integer orderId, Authentication authentication) {

        return userService.findByUserName(authentication.getName()).getOrders().stream()
                .map(order -> order.getId())
                .toList().contains(orderId);
    }

    public boolean userHasRole(Authentication authentication, Role role) {

        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(role.getAuthority())) ;
    }

    public boolean userHasRoleManagerOrAdmin(Authentication authentication) {
        return userHasRole(authentication, Role.MANAGER) || userHasRole(authentication, Role.ADMIN);
    }
}
