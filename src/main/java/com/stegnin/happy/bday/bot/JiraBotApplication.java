package com.stegnin.happy.bday.bot;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JiraBotApplication {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(JiraBotApplication.class, args);
  }

}
