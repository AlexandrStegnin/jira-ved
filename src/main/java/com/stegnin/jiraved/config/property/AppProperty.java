package com.stegnin.jiraved.config.property;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppProperty {

    @NotBlank
    String token;
}
