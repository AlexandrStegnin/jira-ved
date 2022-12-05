package com.stegnin.jiraved.handler;

import com.stegnin.jiraved.utils.enums.UserAction;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Alexandr Stegnin
 */

public interface MessageHandler {
  boolean canHandle(UserAction action);
  BotApiMethod<Message> handleMessage(Update update);
}
