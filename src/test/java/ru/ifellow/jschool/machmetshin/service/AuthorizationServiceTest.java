package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.user.Role;
import ru.ifellow.jschool.machmetshin.entity.user.User;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private Authentication authentication;

    @InjectMocks
    @Spy
    private AuthorizationService spyAuthorizationService;

    @Test
    public void isAdminOrUserWorksWithHisProfileTest() {
        User user = User.builder().id(8).build();
        Mockito.doReturn("dmitry").when(authentication).getName();
        Mockito.doReturn(user).when(userService).findByUserName("dmitry");

        Assertions.assertThat(spyAuthorizationService
                .isAdminOrUserWorksWithHisProfile(8, authentication))
                .isTrue();

        Assertions.assertThat(spyAuthorizationService
                        .isAdminOrUserWorksWithHisProfile(7, authentication))
                .isFalse();

        Mockito.doReturn(true).when(spyAuthorizationService)
            .userHasRole(Mockito.any(), Mockito.eq(Role.ADMIN));

        Assertions.assertThat(spyAuthorizationService
                        .isAdminOrUserWorksWithHisProfile(7, authentication))
                .isTrue();
    }

    @Test
    public void authenticatedUserGotThisOrderTest() {
        List<Order> orders = new ArrayList<>();
        orders.add(Order.builder().id(8).build());

        User user = User.builder().id(8).build();
        user.setOrders(orders);
        Mockito.doReturn("dmitry").when(authentication).getName();
        Mockito.doReturn(user).when(userService).findByUserName("dmitry");

        Assertions.assertThat(spyAuthorizationService
                .authenticatedUserGotThisOrder(8, authentication)).isTrue();
    }

    @Test
    public void userHasRoleTest() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(Role.ADMIN);

        Mockito.doReturn(authorities)
                .when(authentication).getAuthorities();

        Assertions.assertThat(spyAuthorizationService.userHasRole(authentication, Role.ADMIN))
                .isTrue();
    }

    @Test
    public void userHasRoleManagerOrAdminTest() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(Role.ADMIN);

        Mockito.doReturn(authorities)
                .when(authentication).getAuthorities();

        Assertions.assertThat(spyAuthorizationService.userHasRoleManagerOrAdmin(authentication))
                .isTrue();

        List<GrantedAuthority> alternative1Authorities = new ArrayList<>();
        alternative1Authorities.add(Role.MANAGER);
        Mockito.doReturn(alternative1Authorities)
                .when(authentication).getAuthorities();

        Assertions.assertThat(spyAuthorizationService.userHasRoleManagerOrAdmin(authentication))
                .isTrue();

        List<GrantedAuthority> alternative2Authorities = new ArrayList<>();
        alternative2Authorities.add(Role.USER);
        Mockito.doReturn(alternative2Authorities)
                .when(authentication).getAuthorities();

        Assertions.assertThat(spyAuthorizationService.userHasRoleManagerOrAdmin(authentication))
                .isFalse();
    }


}
