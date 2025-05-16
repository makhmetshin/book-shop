package ru.ifellow.jschool.machmetshin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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


import java.util.List;
import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;


@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
@WebAppConfiguration
public class OrderRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final String base_url = "/api/v1/orders";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerAdminRoles")
    public void findByIdAdminManagerTest(String username, String role) throws Exception{

        RequestHelper.setAuthentication(username, role);
        mockMvc.perform(get(base_url)
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice", is(1050)));

    }


    @Test
    @WithMockUser(username = "dmitry", roles = "USER")
    public void findByIdUserNegativeTest() throws Exception {
        mockMvc.perform(get(base_url)
                        .param("id", "1"))
                .andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username = "dmitry", roles = "USER")
    public void findByIdUserPositiveTest() throws Exception {
        mockMvc.perform(get(base_url)
                        .param("id", "8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice", is(950)));
    }
}
