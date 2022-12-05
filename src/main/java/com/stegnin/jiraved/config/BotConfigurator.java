package com.stegnin.jiraved.config;

import com.stegnin.jiraved.JiraBot;
import com.stegnin.jiraved.config.property.TelegramBotProperty;
import com.stegnin.jiraved.handler.UpdateReceiver;
import lombok.experimental.UtilityClass;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Alexandr Stegnin
 */
@UtilityClass
public class BotConfigurator {
  public JiraBot configure(ConfigurableApplicationContext context) {
    var property = context.getBean(TelegramBotProperty.class);
    var updateReceiver = context.getBean(UpdateReceiver.class);
    return new JiraBot(property, updateReceiver);
  }

}
