package ru.ifellow.jschool.machmetshin.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
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
import ru.ifellow.jschool.machmetshin.dto.good.book.BookDto;
import ru.ifellow.jschool.machmetshin.dto.order.BillDto;
import ru.ifellow.jschool.machmetshin.dto.order.CreateOrderDto;
import ru.ifellow.jschool.machmetshin.dto.order.OrderItemDtoWithGoodId;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.DistributeGoodDto;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.FindBooksDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;
import ru.ifellow.jschool.machmetshin.service.BillService;
import ru.ifellow.jschool.machmetshin.service.StorageGoodService;

import java.util.List;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
@WebAppConfiguration
public class ShopManagerRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StorageGoodService storageGoodService;

    private MockMvc mockMvc;
    private final String base_url = "/api/v1/shops";

    private static CreateOrderDto createOrderDto;
    private static DistributeGoodDto distributeGoodDto;
    private RequestDto requestDto;

    @BeforeAll
    public static void prepareDtos() {
        createOrderDto = CreateOrderDto.builder()
                .userId(1)
                .receiveStorageId(1)
                .orderItemDtosWithGoodId(List.of(
                        OrderItemDtoWithGoodId.builder()
                                .goodId(1)
                                .quantity(2)
                                .priceAtPurchase(450)
                                .build(),
                        OrderItemDtoWithGoodId.builder()
                                .goodId(2)
                                .quantity(5)
                                .priceAtPurchase(40)
                                .build()
                ))
                .build();

        distributeGoodDto = DistributeGoodDto.builder()
                .goodId(1)
                .shopIds(List.of(1, 2, 3, 4))
                .warehouseId(11)
                .amount(101)
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
    public void sellAdminManagerTest(String username, String role) throws Exception {

        RequestHelper.setAuthentication(username, role);
        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(post(base_url + "/sell"));
        BillDto responseDto = RequestHelper.send(requestDto, createOrderDto, BillDto.class);

        Assertions.assertThat(responseDto.getShopDto().getId()).isEqualTo(1);
        Assertions.assertThat(storageGoodService.findByStorageIdAndGoodId(1,1).get().getQuantity())
                .isEqualTo(998);
        Assertions.assertThat(storageGoodService.findByStorageIdAndGoodId(1,2).get().getQuantity())
                .isEqualTo(995);
    }

    @Test
    @WithMockUser(username = "dmitry")
    public void sellUserTest() throws Exception {
        requestDto.setExpectedStatus(status().is(403));
        requestDto.setMockMvcRequestBuilder(post(base_url + "/sell"));
        RequestHelper.send(requestDto, createOrderDto);
    }



    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerAdminRoles")
    public void distributeGoodsAdminManagerTest(String username, String role) throws Exception {

        RequestHelper.setAuthentication(username, role);
        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(patch(base_url + "/distribute"));
        RequestHelper.send(requestDto, distributeGoodDto);

        Assertions.assertThat(storageGoodService.findByStorageIdAndGoodId(11,1).get().getQuantity())
                .isEqualTo(899);
        Assertions.assertThat(storageGoodService.findByStorageIdAndGoodId(1,1).get().getQuantity())
                .isEqualTo(1026);
        Assertions.assertThat(storageGoodService.findByStorageIdAndGoodId(2,1).get().getQuantity())
                .isEqualTo(1025);
        Assertions.assertThat(storageGoodService.findByStorageIdAndGoodId(3,1).get().getQuantity())
                .isEqualTo(1025);
        Assertions.assertThat(storageGoodService.findByStorageIdAndGoodId(4,1).get().getQuantity())
                .isEqualTo(1025);
    }

    @Test
    @WithMockUser(username = "dmitry")
    public void distributeGoodsUserTest() throws Exception {
        requestDto.setExpectedStatus(status().is(403));
        requestDto.setMockMvcRequestBuilder(post(base_url + "/distribute"));
        RequestHelper.send(requestDto, distributeGoodDto);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerAdminRoles")
    public void returnGoodsAdminManagerTest(String username, String role) throws Exception {

        RequestHelper.setAuthentication(username, role);

        mockMvc.perform(patch(base_url + "/return")
                        .param("billId", "1"))
                .andExpect(status().isOk());

        Assertions.assertThat(storageGoodService.findByStorageIdAndGoodId(1,2).get().getQuantity())
                .isEqualTo(1001);
    }

    @Test
    @WithMockUser(username = "dmitry")
    public void returnGoodsUserTest() throws Exception {

        mockMvc.perform(patch(base_url + "/return")
                        .param("billId", "1"))
                .andExpect(status().is(403));

    }

    @Test
    public void booksTest() throws Exception {
        FindBooksDto fbDto = FindBooksDto.builder().genre("Detective").authorId(1).build();

        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(post(base_url + "/books").param("shopId", "1"));
        List<BookDto> bookDtos = RequestHelper.send(requestDto, fbDto, new TypeReference<List<BookDto>>(){});

        Assertions.assertThat(bookDtos.getFirst().getTitle())
                .isEqualTo("The Baker Street Mystery");
    }

    @Test
    public void findAllTest() throws Exception {
        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(get(base_url));
        List<StorageDto> storageDtos = RequestHelper.send(requestDto, new TypeReference<List<StorageDto>>(){});

        Assertions.assertThat(storageDtos.size()).isEqualTo(10);
        Assertions.assertThat(storageDtos.get(0).getAddress()).isEqualTo("Lenina St, 1");
    }

    @Test
    public void findByIdTest() throws Exception {
        requestDto.setExpectedStatus(status().isOk());
        requestDto.setMockMvcRequestBuilder(get(base_url + "/{id}", 10));
        StorageDto storageDto = RequestHelper.send(requestDto, StorageDto.class);

        Assertions.assertThat(storageDto.getAddress()).isEqualTo("Dostoevskogo St, 15");
    }

}
