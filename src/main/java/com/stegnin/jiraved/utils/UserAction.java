package com.stegnin.jiraved.utils;

import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum UserAction {
    INSTRUCTION,
    PARSE_MESSAGE,
    SAVE_CONTACT,
    SEND_WELCOME,
    START_CONVERSATION;

    public static UserAction resolveAction(Update update, boolean isUserRegistered) {
        if (MessageUtils.isStartMessage(update)) {
            return UserAction.START_CONVERSATION;
        }
        if ((isUserRegistered && MessageUtils.isCommandMessage(update))) {
            return UserAction.INSTRUCTION;
        } else if (isUserRegistered) {
            return UserAction.PARSE_MESSAGE;
        }
        if (MessageUtils.isContactMessage(update)) {
            return UserAction.SAVE_CONTACT;
        }
        return UserAction.SEND_WELCOME;
    }

    public static UserAction resolveCallbackAction(Update update) {
        var callbackData = update.getCallbackQuery();
        return Stream.of(values())
                .filter(action -> action.name().equalsIgnoreCase(callbackData.getData()))
                .findFirst()
                .orElse(UserAction.INSTRUCTION);
    }
}
