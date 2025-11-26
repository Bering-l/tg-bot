package ru.lesson.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Жизненный цикл Polling
 */

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botName;
    private static final Logger log = LogManager.getLogger(TelegramBot.class);

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var originMessage = update.getMessage();
            // Обработка текстового сообщения
            if (originMessage.hasText()) {
                log.info("Получено сообщение: {}", originMessage.getText());
            SendMessage response = SendMessage.builder()
                    .chatId(originMessage.getChatId())
                    .text("Hello!")
                    .build();
                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    log.warn("Ошибка отправки ответа");
                }
            }
        }
    }

    /**
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return botName;
    }

    /**
     * Конструктор для токена
     *
     * @param botToken
     */
    public TelegramBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }
}
