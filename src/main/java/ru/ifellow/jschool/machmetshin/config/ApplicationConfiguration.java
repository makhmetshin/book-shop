package ru.ifellow.jschool.machmetshin.config;


import ru.ifellow.jschool.machmetshin.dao.BillDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.ifellow.jschool.machmetshin")
public class ApplicationConfiguration {

//    @Bean
//    public BillDao billDao() {
//        return new BillDao();
//    }
}
