package com.example.seedling.book.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "book")
@Schema(description = "书籍信息")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "description", columnDefinition = "text")
  private String description;

  @PersistenceConstructor
  protected Book() {}

  public Book(String title, String author, String description) {
    this.title = title;
    this.author = author;
    this.description = description;
  }

  public static Book ofTitle(String title) {
    return new Book(title, null, null);
  }
}
