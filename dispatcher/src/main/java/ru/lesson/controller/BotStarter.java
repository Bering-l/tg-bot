package ru.lesson.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotStarter implements CommandLineRunner {

    private final TelegramBot telegramBot;

    public BotStarter(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            System.err.println(e.getMessage());
        }
    }
}
