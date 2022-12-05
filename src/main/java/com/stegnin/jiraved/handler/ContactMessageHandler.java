package com.stegnin.jiraved.handler;

import com.stegnin.jiraved.service.BotMessageBuilder;
import com.stegnin.jiraved.storage.service.AppUserService;
import com.stegnin.jiraved.utils.UserAction;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author Alexandr Stegnin
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ContactMessageHandler implements MessageHandler {

    BotMessageBuilder botMessageBuilder;
    AppUserService appUserService;

    @Override
    public boolean canHandle(UserAction action) {
        return UserAction.SAVE_CONTACT == action;
    }

    @Override
    public BotApiMethod<Message> handleMessage(Update update) {
        var contact = update.getMessage().getContact();
        if (isSameContact(contact, update.getMessage().getFrom())) {
            var user = appUserService.save(contact);
            log.info("Send greeting message to user: {}", user.getPhone());
            return botMessageBuilder.buildGreetingMessage(user);
        }
        log.info("Send repeat request contact message");
        return botMessageBuilder.buildRepeatRequestContactMessage(update);
    }

    private boolean isSameContact(Contact contact, User user) {
        return Objects.equals(contact.getUserId(), user.getId());
    }

}
