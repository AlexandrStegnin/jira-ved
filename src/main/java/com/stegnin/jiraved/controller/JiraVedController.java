package com.stegnin.jiraved.controller;

import com.stegnin.jiraved.config.property.AppProperty;
import java.util.Objects;
import javax.ws.rs.BadRequestException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class JiraVedController {

    AppProperty appProperty;

    @PostMapping(path = "/{token}/jira")
    public String handleJiraRequest(@PathVariable String token, @RequestBody Object jiraObject) {
        log.info("Handle Jira request");
        checkToken(token);
        log.info("Jira request {}", jiraObject);
        return "Success";
    }

    private void checkToken(String token) {
        if (!Objects.equals(token, appProperty.getToken())) {
            throw new BadRequestException("Token invalid");
        }
    }

}
