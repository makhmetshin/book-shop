package ru.ifellow.jschool.machmetshin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.config.jpa.DataInitializer;
import ru.ifellow.jschool.machmetshin.controller.utils.RequestHelper;
import ru.ifellow.jschool.machmetshin.controller.utils.dto.RequestDto;
import ru.ifellow.jschool.machmetshin.dto.order.BillDto;
import ru.ifellow.jschool.machmetshin.dto.order.CreateOrderDto;
import ru.ifellow.jschool.machmetshin.dto.order.OrderItemDtoWithGoodId;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.DistributeGoodDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodGetAmountDto;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;
import ru.ifellow.jschool.machmetshin.service.BillService;
import ru.ifellow.jschool.machmetshin.service.StorageGoodService;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
@WebAppConfiguration
public class StorageGoodRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StorageGoodService storageGoodService;

    private MockMvc mockMvc;
    private final String base_url = "/api/v1/storages";
    private RequestDto requestDto;
    private static StorageGoodGetAmountDto storageGoodGetAmountDto;

    @BeforeAll
    public static void prepareDtos() {
        storageGoodGetAmountDto = StorageGoodGetAmountDto.builder()
                .storageId(11)
                .goodId(1)
                .storageType(StorageType.WAREHOUSE)
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
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerAdminRoles")
    public void getAmountOfGoodAdminManagerTest(String username, String role) throws Exception {

        RequestHelper.setAuthentication(username, role);
        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(post(base_url));

        Integer amount = RequestHelper.send(requestDto, storageGoodGetAmountDto, Integer.class);
        Assertions.assertThat(amount).isEqualTo(1000);

    }

    @WithMockUser(username = "dmitry")
    @Test
    public void getAmountOfGoodUserTest() throws Exception {

        requestDto.setExpectedStatus(status().is(403));
        requestDto.setMockMvcRequestBuilder(post(base_url));
        RequestHelper.send(requestDto, storageGoodGetAmountDto);
    }
}
