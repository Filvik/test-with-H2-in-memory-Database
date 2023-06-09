package ru.skillfactorydemo.tgbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TgbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgbotApplication.class, args);
    }
}