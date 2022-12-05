package com.stegnin.jiraved.config.property;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author Alexandr Stegnin
 */
@Configuration
@ConfigurationProperties(prefix = "telegram-bot")
@Data
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramBotProperty {

    @NotBlank
    String name;
    @NotBlank
    String token;
}
