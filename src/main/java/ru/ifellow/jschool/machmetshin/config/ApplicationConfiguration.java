package ru.ifellow.jschool.machmetshin.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.ifellow.jschool.machmetshin")
public class ApplicationConfiguration {

    //подобное закомментированое советую сразу удалять, иначе проект быстро мусором зарастёт)
//    @Bean
//    public BillDao billDao() {
//        return new BillDao();
//    }
}
