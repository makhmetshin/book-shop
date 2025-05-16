package ru.ifellow.jschool.machmetshin.controller.utils;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class UserProvider {

    private static Stream<Arguments> provideUsersWithManagerAdminRoles() {
        return Stream.of(
                Arguments.of("yulia", "ADMIN"),
                Arguments.of("maxim", "MANAGER")
        );
    }

    private static Stream<Arguments> provideUsersWithAdminUserRoles() {
        return Stream.of(
                Arguments.of("yulia", "ADMIN"),
                Arguments.of("dmitry", "USER")
        );
    }

    private static Stream<Arguments> provideUsersWithManagerUserRoles() {
        return Stream.of(
                Arguments.of("maxim", "MANAGER"),
                Arguments.of("dmitry", "USER")
        );
    }
}
