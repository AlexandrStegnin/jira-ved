package com.stegnin.jiraved;

import com.stegnin.jiraved.config.property.TelegramBotProperty;
import com.stegnin.jiraved.handler.UpdateReceiver;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Alexandr Stegnin
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class JiraBot extends TelegramLongPollingBot {

    TelegramBotProperty telegramBotProperty;
    UpdateReceiver updateReceiver;

    @Override
    public String getBotUsername() {
        return telegramBotProperty.getName();
    }

    @Override
    public String getBotToken() {
        return telegramBotProperty.getToken();
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        var method = updateReceiver.handleUpdate(update);
        execute(method);
    }

}
