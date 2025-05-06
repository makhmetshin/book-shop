package ru.ifellow.jschool.machmetshin.config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.ifellow.jschool.machmetshin.config.jpa.JpaConfiguration;
import ru.ifellow.jschool.machmetshin.config.web.WebConfiguration;

@Configuration
@Import({JpaConfiguration.class})//, WebConfiguration.class
@ComponentScan(basePackages = "ru.ifellow.jschool.machmetshin")
public class ApplicationConfiguration {



}
