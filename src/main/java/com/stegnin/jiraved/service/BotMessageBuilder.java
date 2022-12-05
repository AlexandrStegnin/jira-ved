package com.stegnin.jiraved.service;

import com.stegnin.jiraved.storage.model.AppUser;
import com.stegnin.jiraved.utils.MessageUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Alexandr Stegnin
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BotMessageBuilder {

    BotButtonManager botButtonManager;

    public SendMessage buildWelcomeMessage(Update update) {
        var message = buildSendMessage(
                update.getMessage().getChatId().toString(),
                MessageUtils.getWelcomeMessage()
        );

        botButtonManager.addRequestContactButton(message);
        return message;
    }

    public SendMessage buildGreetingMessage(AppUser user) {
        return buildSendMessage(
                user.getId().toString(),
                MessageUtils.getGreetingMessage(user.getUsername())
        );
    }

    public SendMessage buildRepeatRequestContactMessage(Update update) {
        return buildSendMessage(
                update.getMessage().getChatId().toString(),
                MessageUtils.getRepeatRequestContactMessage()
        );
    }

    private SendMessage buildSendMessage(String chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .parseMode(ParseMode.HTML)
                .build();
    }

}
