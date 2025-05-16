package ru.ifellow.jschool.machmetshin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.config.jpa.DataInitializer;
import ru.ifellow.jschool.machmetshin.controller.utils.RequestHelper;
import ru.ifellow.jschool.machmetshin.controller.utils.dto.RequestDto;
import ru.ifellow.jschool.machmetshin.dto.user.CreateUserDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserAccountDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserOrdersDto;
import ru.ifellow.jschool.machmetshin.entity.user.Role;
import ru.ifellow.jschool.machmetshin.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
@WebAppConfiguration
public class UserRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;
    private final String base_url = "/api/v1/users";

    private RequestDto requestDto;
    private static UserAccountDto userAccountDto;
    private static CreateUserDto createUserDto;
    private static CreateUserDto createManagerDto;
    private static CreateUserDto createAdminDto;

    @BeforeAll
    public static void prepareDtos() {
        userAccountDto = UserAccountDto.builder()
                .username("newUsername")
                .name("newName")
                .surname("newSurname")
                .lastName("last")
                .email("example.example@example.com")
                .build();

        createUserDto = CreateUserDto.builder()
                .username("sampleUser")
                .password("samplePassword")
                .name("SampleName")
                .surname("SampleSurname")
                .lastName("SampleLastName")
                .email("sample@example.com")
                .role("USER")
                .build();

        createManagerDto = CreateUserDto.builder()
                .username("sampleManager")
                .password("samplePassword")
                .name("SampleName")
                .surname("SampleSurname")
                .lastName("SampleLastName")
                .email("sample@example.com")
                .role("MANAGER")
                .build();

        createAdminDto = CreateUserDto.builder()
                .username("sampleAdmin")
                .password("samplePassword")
                .name("SampleName")
                .surname("SampleSurname")
                .lastName("SampleLastName")
                .email("sample@example.com")
                .role("ADMIN")
                .build();


    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        requestDto = RequestDto.builder().mockMvc(mockMvc).objectMapper(objectMapper).build();
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithAdminUserRoles")
    public void findByIdTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);

        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(get(base_url).param("id", "8"));
        UserOrdersDto userOrdersDto = RequestHelper.send(requestDto, UserOrdersDto.class);

        Assertions.assertThat(userOrdersDto.getName()).isEqualTo("Dmitry");
        Assertions.assertThat(userOrdersDto.getOrdersIds()).hasSize(1);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerUserRoles")
    public void findByIdNegativeTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);

        requestDto.setExpectedStatus(status().is(403));
        requestDto.setMockMvcRequestBuilder(get(base_url).param("id", "7"));
        RequestHelper.send(requestDto);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithAdminUserRoles")
    public void accountInformationTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);

        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(get(base_url + "/account").param("id", "8"));
        UserAccountDto userAccountDto = RequestHelper.send(requestDto, UserAccountDto.class);

        Assertions.assertThat(userAccountDto.getName()).isEqualTo("Dmitry");

    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerUserRoles")
    public void accountInformationNegativeTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);

        requestDto.setExpectedStatus(status().is(403));
        requestDto.setMockMvcRequestBuilder(get(base_url + "/account").param("id", "7"));
        RequestHelper.send(requestDto);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithAdminUserRoles")
    public void updateAccountDetailsTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);

        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(patch(base_url + "/account")
                .param("id", "8"));
        RequestHelper.send(requestDto, userAccountDto);

        Assertions.assertThat(userService.findById(8).get().getName())
                .isEqualTo("newName");
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerUserRoles")
    public void updateAccountDetailsNegativeTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);

        requestDto.setExpectedStatus(status().is(403));
        requestDto.setMockMvcRequestBuilder(patch(base_url + "/account")
                .param("id", "7"));
        RequestHelper.send(requestDto, userAccountDto);
    }

    @WithMockUser(username = "yulia", roles = "ADMIN")
    @Test
    public void registerByAdminTest() throws Exception {

        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(post(base_url + "/register"));
        RequestHelper.send(requestDto, createUserDto);
        RequestHelper.send(requestDto, createManagerDto);
        RequestHelper.send(requestDto, createAdminDto);

        Assertions.assertThat(userService.findByUserName("sampleUser").getRole())
                .isEqualTo(Role.USER);
        Assertions.assertThat(userService.findByUserName("sampleManager").getRole())
                .isEqualTo(Role.MANAGER);
        Assertions.assertThat(userService.findByUserName("sampleAdmin").getRole())
                .isEqualTo(Role.ADMIN);
    }

    @WithMockUser(username = "dmitry", roles = "USER")
    @Test
    public void registerByUserTest() throws Exception {

        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(post(base_url + "/register"));
        RequestHelper.send(requestDto, createUserDto);

        requestDto.setExpectedStatus(status().is(403));
        RequestHelper.send(requestDto, createManagerDto);
        RequestHelper.send(requestDto, createAdminDto);

        Assertions.assertThat(userService.findByUserName("sampleUser").getRole())
                .isEqualTo(Role.USER);
    }

    @WithMockUser(username = "maxim", roles = "MANAGER")
    @Test
    public void registerByManagerNegativeTest() throws Exception {

        requestDto.setExpectedStatus(status().is(403));
        requestDto.setMockMvcRequestBuilder(post(base_url + "/register"));
        RequestHelper.send(requestDto, createUserDto);
        RequestHelper.send(requestDto, createManagerDto);
        RequestHelper.send(requestDto, createAdminDto);
    }


    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithAdminUserRoles")
    public void deleteTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);

        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(delete(base_url + "/delete")
                .param("id", "8"));
        RequestHelper.send(requestDto);
        org.junit.jupiter.api.Assertions
                .assertThrows(UsernameNotFoundException.class, () -> userService.findByUserName("dmitry"));
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerUserRoles")
    public void deleteByUserManagerNegativeTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);
        requestDto.setExpectedStatus(status().is(403));
        requestDto.setMockMvcRequestBuilder(delete(base_url + "/delete")
                .param("id", "7"));
        RequestHelper.send(requestDto);
    }

}
