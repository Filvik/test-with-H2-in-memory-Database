package ru.skillfactorydemo.tgbot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import javax.activation.DataSource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;

// указываем, что это конфигурация, которая опирается на такой-то пакет и такой-то config-файл, плюс позволяет управлять транзакциями
@Configuration
@EnableJpaRepositories(basePackages = "ru.skillfactorydemo.tgbot.repository")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class MyJpaConfig {

    // сервис текущего окружения (с параметрами и т.д.)
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {


        System.out.println("!!!\n" + env.getProperty("spring.datasource.driverClassName") + "\n!!!!!");

        // настраиваем, откуда мы будем брать данные: адрес/логин/пароль/драйвер
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    // дальше можно сконфигурировать entityManagerFactory, transactionManager, прочите дополнительные свойства Hibernate
    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ru.skillfactorydemo.tgbot.entity");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}