package ru.ifellow.jschool.machmetshin.config.jpa;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import ru.ifellow.jschool.machmetshin.database.repository.AuthorRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;

import javax.sql.DataSource;
import java.util.List;

@Component
public class DataInitializer implements SmartInitializingSingleton {

    private final DataSource dataSource;
    @Autowired
    private AuthorRepository authorRepository;

    public DataInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void afterSingletonsInstantiated() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data.sql"));
        DatabasePopulatorUtils.execute(populator, dataSource);

        System.out.println("Program has just begun its work.");
        List<Author> authorList = authorRepository.findAll();
        if (authorList.isEmpty()) {
            System.out.println("Author list is empty");
        }
        for (Author author : authorList) {
            System.out.println(author);
        }
    }
}
