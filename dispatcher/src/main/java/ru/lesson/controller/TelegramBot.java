package ru.lesson.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Жизненный цикл Polling
 */

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var originMessage = update.getMessage();
            // Обработка текстового сообщения
            if (originMessage.hasText()) {
                System.out.println("Получено сообщение: " + originMessage.getText());
            }
        }
    }

    /**
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "aejfub_bot";
    }

    /**
     * Конструктор для токена
     * @param botToken
     */
    public TelegramBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }
}
