package com.example.seedling.book;

import com.example.seedling.common.SeedlingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

@Tag(name = "书籍管理")
@RestController
public class BookController {
  private final BookRepository books;

  public BookController(BookRepository books) {
    this.books = books;
  }

  @Operation(summary = "获取书籍列表")
  @GetMapping("/book/{title}")
  public Book getBookByTitle(@Parameter(description = "书籍标题") @PathVariable("title") String title) {
    return books
        .findOne(Example.of(Book.ofTitle(title)))
        .orElseThrow(
            () -> {
              throw new SeedlingException(String.format("标题为: %s的书籍不存在", title));
            });
  }

  @Operation(summary = "登记书籍")
  @PostMapping("/book")
  public Book registerBook(@RequestBody RegisterBookRequest request) {
    return books.save(new Book(request.title(), request.author(), request.description()));
  }
}
