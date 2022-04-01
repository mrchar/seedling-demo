package com.example.seedling;

import com.example.seedling.book.Book;
import com.example.seedling.book.BookRepository;
import com.example.seedling.system.User;
import com.example.seedling.system.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SeedlingApplication {
  private final BookRepository books;
  private final UserRepository users;

  public SeedlingApplication(BookRepository books, UserRepository users) {
    this.books = books;
    this.users = users;
  }

  public static void main(String[] args) {
    SpringApplication.run(SeedlingApplication.class, args);
  }

  @PostConstruct
  public void initData() {
    books.save(new Book("title", "author", "description"));
    users.save(new User("user", "password"));
  }
}
