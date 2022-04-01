package com.example.seedling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SeedlingApplication {

  public static void main(String[] args) {
    SpringApplication.run(SeedlingApplication.class, args);
  }

  @RequestMapping("/**")
  public String greeting() {
    return "hello world";
  }
}
