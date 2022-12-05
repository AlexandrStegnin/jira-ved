package com.stegnin.jiraved.handler;

import com.stegnin.jiraved.service.BotMessageBuilder;
import com.stegnin.jiraved.utils.UserAction;
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
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class StartConversationHandler implements MessageHandler {

    BotMessageBuilder botMessageBuilder;

    @Override
    public boolean canHandle(UserAction action) {
        return UserAction.START_CONVERSATION == action;
    }

    @Override
    public BotApiMethod<Message> handleMessage(Update update) {
        log.info("Send welcome message");
        return botMessageBuilder.buildWelcomeMessage(update);
    }

}
