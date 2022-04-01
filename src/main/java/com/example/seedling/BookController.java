package com.example.seedling;

import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
  private final BookRepository books;

  public BookController(BookRepository books) {
    this.books = books;
  }

  @GetMapping("/book/{title}")
  public Book getBookByTitle(@PathVariable("title") String title) {
    return books
        .findOne(Example.of(Book.ofTitle(title)))
        .orElseThrow(
            () -> {
              throw new SeedlingException(String.format("标题为: %s的书籍不存在", title));
            });
  }
}