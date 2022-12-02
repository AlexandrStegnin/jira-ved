package com.stegnin.jira.ved.config;

import com.stegnin.jira.JiraBot;
import com.stegnin.jira.ved.config.property.TelegramBotProperty;
import com.stegnin.jira.handler.UpdateReceiver;
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
