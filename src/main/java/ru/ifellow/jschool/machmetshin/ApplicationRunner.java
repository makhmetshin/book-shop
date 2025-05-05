package ru.ifellow.jschool.machmetshin;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.database.repository.AuthorRepository;
import ru.ifellow.jschool.machmetshin.database.repository.BookRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;


@AllArgsConstructor
@Component
public class ApplicationRunner {


    public static void main(String[] args) throws LifecycleException, InterruptedException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationConfiguration.class);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir(".");

        tomcat.getConnector(); // неочевидный шаг но без него не заработает
        // https://stackoverflow.com/questions/56668892/embedded-tomcat-java-application-is-running-but-server-cannot-be-reached/61394907

        var base = new File(".").getAbsolutePath();
        var ctx = tomcat.addContext("", base);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        Tomcat.addServlet(ctx, "dispatcher", dispatcherServlet).setLoadOnStartup(1);
        ctx.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();
        System.out.println("Server started");
        tomcat.getServer().await();
    }
}
