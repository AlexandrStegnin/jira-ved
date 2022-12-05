package com.stegnin.jiraved.handler;

import com.stegnin.jiraved.utils.MessageUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Alexandr Stegnin
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateReceiver {

    UserActionHandler userActionHandler;

    public BotApiMethod<Message> handleUpdate(Update update) {
        if (MessageUtils.isCallbackMessage(update)) {
            return handleCallback(update);
        }
        return handleText(update);
    }

    public BotApiMethod<Message> handleText(Update update) {
        var message = update.getMessage();
        log.info("Message from: {}; chat id: {}; text: {}", message.getFrom().getUserName(), message.getChatId(),
                message.getText());
        return userActionHandler.handle(update);
    }

    public BotApiMethod<Message> handleCallback(Update update) {
        var message = update.getCallbackQuery().getMessage();
        log.info("Message from: {}; chat id: {}; text: {}", message.getFrom().getUserName(), message.getChatId(),
                update.getCallbackQuery().getData());
        return userActionHandler.handle(update);
    }

}
