package org.example.config;


import org.example.dao.BillDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class ApplicationConfiguration {

    @Bean
    public BillDao billDao() {
        return new BillDao();
    }
}
