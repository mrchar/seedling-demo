package com.example.seedling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SeedlingApplication {
  private final BookRepository books;

  public SeedlingApplication(BookRepository books) {
    this.books = books;
  }

  public static void main(String[] args) {
    SpringApplication.run(SeedlingApplication.class, args);
  }

  @PostConstruct
  public void initData() {
    books.save(new Book("title", "author", "description"));
  }
}
