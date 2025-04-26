package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.context.SpringBootTest;
import ru.ifellow.jschool.machmetshin.database.repository.BillRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.service.BillService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class BillServiceTest  {

    @Mock
    private BillRepository billRepository;


    @InjectMocks
    private BillService billService;

    private List<Bill> bills = new ArrayList<>();

    @BeforeEach
    public void setup() {
        for (int i = 0; i < 11; i++)
            bills.add(new Bill());
    }

    @Test
    public void saveTest() {

        Mockito.doReturn(new Bill()).when(billRepository).save(Mockito.any(Bill.class));
        Mockito.doReturn(bills).when(billRepository).findAll();

        billService.save(new Bill());
        Assertions.assertThat(billService.findAll()).hasSize(11);
        Mockito.verify(billRepository, Mockito.times(1)).save(Mockito.any(Bill.class));
    }

    @Test
    public void findAllTest() {
        Mockito.doReturn(bills).when(billRepository).findAll();
        Assertions.assertThat(billService.findAll()).hasSize(11);
    }

    @Test
    public void findByIdTest() {
        Mockito.doReturn(Optional.ofNullable(Bill.builder().id(1).build()) )
                .when(billRepository).findById(1);

        Assertions.assertThat(billService.findById(1).get().getId()).isEqualTo(1);
    }
}
