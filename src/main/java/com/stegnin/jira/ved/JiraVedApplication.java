package com.stegnin.jira.ved;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JiraVedApplication {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(JiraVedApplication.class, args);
  }

}
