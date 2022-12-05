package com.stegnin.jiraved;

import com.stegnin.jiraved.config.BotConfigurator;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@EnableScheduling
@SpringBootApplication
public class JiraVedApplication {

    @SneakyThrows
    public static void main(String[] args) {
        var context = SpringApplication.run(JiraVedApplication.class, args);
        var bot = BotConfigurator.configure(context);
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
    }

}
