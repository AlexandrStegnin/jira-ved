package com.stegnin.jiraved.controller;

import com.stegnin.jiraved.config.JsonExtension;
import com.stegnin.jiraved.config.property.AppProperty;
import java.util.Objects;
import javax.ws.rs.BadRequestException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ExtensionMethod(JsonExtension.class)
@RequiredArgsConstructor
@Slf4j
public class JiraVedController {

    AppProperty appProperty;

    @PostMapping(path = "/{token}/jira")
    public String handleJiraRequest(@PathVariable String token, @RequestBody Object jiraObject) {
        log.info("Handle Jira request {}", jiraObject.toJson());
        checkToken(token);
        return "Success";
    }

    private void checkToken(String token) {
        if (!Objects.equals(token, appProperty.getToken())) {
            log.error("Invalid token");
            throw new BadRequestException("Invalid token");
        }
    }

}