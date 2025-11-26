package ru.lesson.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotStarter implements CommandLineRunner {

    private final TelegramBot telegramBot;
    private static final Logger log = LogManager.getLogger(BotStarter.class);

    public BotStarter(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void run(String... args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            log.debug(e.getMessage(),
                    "Регистрация бота по API");
        }
    }
}
