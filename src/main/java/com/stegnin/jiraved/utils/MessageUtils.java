package com.stegnin.jiraved.utils;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Alexandr Stegnin
 */
@UtilityClass
public class MessageUtils {

  public boolean isCommandMessage(Update update) {
    return update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/");
  }
  public boolean isContactMessage(Update update) {
    return update.hasMessage() && update.getMessage().hasContact();
  }
  public boolean isCallbackMessage(Update update) {
    return update.hasCallbackQuery();
  }
  public String getWelcomeMessage() {
    return """
      Привет %s! Похоже мы незнакомы.
      Я помогу тебе не забыть про Time to market по твоим ЗНИ %s!
      Поделись со мной своим номером телефона %s
      Так я буду уверен, что общаюсь именно с тобой.
      Нажми кнопку 'Поделиться номером' %s""".formatted(Emoji.HELLO, Emoji.CAKE, Emoji.IPHONE, Emoji.FINGER_DOWN);
  }

  public String getGreetingMessage(String username) {
    return """
      Приятно познакомиться, %s! %s
      
      """.formatted(username, Emoji.DEAL);
  }
}
