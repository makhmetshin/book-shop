package ru.ifellow.jschool.machmetshin;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.database.repository.AuthorRepository;
import ru.ifellow.jschool.machmetshin.database.repository.BookRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;

import javax.sql.DataSource;
import java.util.List;


@AllArgsConstructor
@Component
public class ApplicationRunner {


    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {

        }
    }
}
