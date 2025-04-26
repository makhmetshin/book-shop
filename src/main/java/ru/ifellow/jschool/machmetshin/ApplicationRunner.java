package ru.ifellow.jschool.machmetshin;

import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ifellow.jschool.machmetshin.database.repository.BookRepository;

import javax.sql.DataSource;


@SpringBootApplication
@AllArgsConstructor
public class ApplicationRunner  {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
        System.out.println("Программа начала выполнение.");
    }
}
