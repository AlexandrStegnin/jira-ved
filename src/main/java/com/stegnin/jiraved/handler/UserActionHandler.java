package com.stegnin.jiraved.handler;

import com.stegnin.jiraved.exception.HandlerNotFoundException;
import com.stegnin.jiraved.storage.service.AppUserService;
import com.stegnin.jiraved.utils.MessageUtils;
import com.stegnin.jiraved.utils.UserAction;
import java.util.List;
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
public class UserActionHandler {

  List<MessageHandler> messageHandlers;
  AppUserService appUserService;

  public BotApiMethod<Message> handle(Update update) {
    if (MessageUtils.isCallbackMessage(update)) {
      return handleCallback(update);
    }
    return handleMessage(update);
  }

  public BotApiMethod<Message> handleMessage(Update update) {
    var isUserRegistered = appUserService.isRegisteredUser(update.getMessage().getFrom().getId());
    UserAction action = UserAction.resolveAction(update, isUserRegistered);
    log.info("Resolved action {}", action);

    var messageHandler = messageHandlers.stream()
        .filter(m -> m.canHandle(action))
        .findAny()
        .orElseThrow(HandlerNotFoundException::new);

    return messageHandler.handleMessage(update);
  }

  private BotApiMethod<Message> handleCallback(Update update) {
    var action = UserAction.resolveCallbackAction(update);
    log.info("Resolved action {}", action);
    var messageHandler = messageHandlers.stream()
        .filter(m -> m.canHandle(action))
        .findAny()
        .orElseThrow(HandlerNotFoundException::new);

    return messageHandler.handleMessage(update);
  }

}
