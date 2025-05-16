package ru.ifellow.jschool.machmetshin.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.config.jpa.DataInitializer;
import ru.ifellow.jschool.machmetshin.controller.utils.RequestHelper;
import ru.ifellow.jschool.machmetshin.controller.utils.dto.RequestDto;
import ru.ifellow.jschool.machmetshin.dto.good.book.BookDto;
import ru.ifellow.jschool.machmetshin.dto.order.CreateWebOrderDto;
import ru.ifellow.jschool.machmetshin.dto.order.OrderItemDtoWithGoodId;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.FindBooksDto;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;
import ru.ifellow.jschool.machmetshin.service.BillService;
import ru.ifellow.jschool.machmetshin.service.OrderService;
import ru.ifellow.jschool.machmetshin.service.StorageGoodService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
@WebAppConfiguration
public class WebShopManagerRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private static CreateWebOrderDto createWebOrderDto;
    private static CreateWebOrderDto alternativeIdCreateWebOrderDto;
    private static FindBooksDto findBooksDto;
    private MockMvc mockMvc;
    private final String base_url = "/api/v1/web_shop";
    private RequestDto requestDto;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BillService billService;
    @Autowired
    private StorageGoodService storageGoodService;

    @BeforeAll
    public static void prepareDtos() {
        createWebOrderDto = CreateWebOrderDto.builder()
                .userId(8)
                .receiveStorageId(1)
                .warehouseId(11)
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

        alternativeIdCreateWebOrderDto = CreateWebOrderDto.builder()
                .userId(7)
                .receiveStorageId(1)
                .warehouseId(11)
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
        findBooksDto = FindBooksDto.builder().authorId(1).genre("Detective").build();
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
    public void createOrderTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);
        requestDto.setMockMvcRequestBuilder(post(base_url + "/create_order"));
        requestDto.setExpectedStatus(status().isOk());

        RequestHelper.send(requestDto, createWebOrderDto);

        Assertions.assertThat(orderService.findByUserId(8))
                .hasSize(2);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerUserRoles")
    public void createOrderNegativeTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);
        requestDto.setMockMvcRequestBuilder(post(base_url + "/create_order"));
        requestDto.setExpectedStatus(status().is(403));

        RequestHelper.send(requestDto, alternativeIdCreateWebOrderDto);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithAdminUserRoles")
    public void cancelOrderTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);
        requestDto.setMockMvcRequestBuilder(patch(base_url + "/cancel_order")
                .param("orderId", "8"));
        requestDto.setExpectedStatus(status().isOk());

        RequestHelper.send(requestDto);

        Assertions.assertThat(orderService.findById(8).get().getOrderStatus())
                .isEqualTo(OrderStatus.CANCELLED);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerUserRoles")
    public void cancelOrderNegativeTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);
        requestDto.setMockMvcRequestBuilder(patch(base_url + "/cancel_order")
                .param("orderId", "7"));
        requestDto.setExpectedStatus(status().is(403));

        RequestHelper.send(requestDto);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerAdminRoles")
    public void changeOrderStatusTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);
        requestDto.setMockMvcRequestBuilder(patch(base_url + "/change_order_status")
                .param("orderId", "8")
                .param("status", "TRANSIT"));
        requestDto.setExpectedStatus(status().isOk());

        RequestHelper.send(requestDto);

        Assertions.assertThat(orderService.findById(8).get().getOrderStatus())
                .isEqualTo(OrderStatus.TRANSIT);
    }

    @Test
    @WithMockUser(username = "dmitry")
    public void changeOrderStatusNegativeTest() throws Exception {

        requestDto.setMockMvcRequestBuilder(patch(base_url + "/change_order_status")
                .param("orderId", "8")
                .param("status", "TRANSIT"));
        requestDto.setExpectedStatus(status().is(403));
        RequestHelper.send(requestDto);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerAdminRoles")
    public void takeawayOrderTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);
        requestDto.setMockMvcRequestBuilder(patch(base_url + "/takeaway_order")
                .param("orderId", "8"));
        requestDto.setExpectedStatus(status().isOk());

        RequestHelper.send(requestDto);

        Assertions.assertThat(orderService.findById(8).get().getOrderStatus())
                .isEqualTo(OrderStatus.FINISHED);
    }

    @Test
    @WithMockUser(username = "dmitry")
    public void takeawayOrderTest() throws Exception {

        requestDto.setMockMvcRequestBuilder(patch(base_url + "/takeaway_order")
                .param("orderId", "8"));
        requestDto.setExpectedStatus(status().is(403));
        RequestHelper.send(requestDto);
    }

    @ParameterizedTest
    @MethodSource("ru.ifellow.jschool.machmetshin.controller.utils.UserProvider#provideUsersWithManagerAdminRoles")
    public void returnGoodsTest(String username, String role) throws Exception {
        RequestHelper.setAuthentication(username, role);
        requestDto.setMockMvcRequestBuilder(patch(base_url + "/return")
                .param("billId", "1"));
        requestDto.setExpectedStatus(status().isOk());

        RequestHelper.send(requestDto);

        Assertions.assertThat(billService.findById(1).get().getReturned())
                .isTrue();
        Assertions.assertThat(storageGoodService
                        .findByStorageIdAndGoodId(1, 2).get().getQuantity())
                .isEqualTo(1001);

    }

    @Test
    @WithMockUser(username = "dmitry")
    public void returnGoodsTest() throws Exception {

        requestDto.setMockMvcRequestBuilder(patch(base_url + "/return")
                .param("billId", "1"));
        requestDto.setExpectedStatus(status().is(403));
        RequestHelper.send(requestDto);
    }

    @Test
    public void findBooksTest() throws Exception {

        requestDto.setMockMvcRequestBuilder(post(base_url + "/books"));
        requestDto.setExpectedStatus(status().isOk());

        List<BookDto> bookDtos = RequestHelper
                .send(requestDto, findBooksDto, new TypeReference<List<BookDto>>() {});
        Assertions.assertThat(bookDtos.size()).isEqualTo(1);
        Assertions.assertThat(bookDtos.get(0).getTitle())
                .isEqualTo("The Baker Street Mystery");
    }

}
