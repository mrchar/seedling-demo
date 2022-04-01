package com.example.seedling;

import com.example.seedling.book.model.Book;
import com.example.seedling.book.repository.BookRepository;
import com.example.seedling.system.model.Authority;
import com.example.seedling.system.model.Role;
import com.example.seedling.system.model.Roles;
import com.example.seedling.system.model.User;
import com.example.seedling.system.repository.AuthorityRepository;
import com.example.seedling.system.repository.RoleRepository;
import com.example.seedling.system.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Set;

@SpringBootApplication
public class SeedlingApplication {
  private final BookRepository books;
  private final UserRepository users;
  private final RoleRepository roles;
  private final AuthorityRepository authorities;

  public SeedlingApplication(
      BookRepository books,
      UserRepository users,
      RoleRepository roles,
      AuthorityRepository authorities) {
    this.books = books;
    this.users = users;
    this.roles = roles;
    this.authorities = authorities;
  }

  public static void main(String[] args) {
    SpringApplication.run(SeedlingApplication.class, args);
  }

  @PostConstruct
  public void initData() {
    books.save(new Book("title", "author", "description"));

    Authority authorityGetBook = new Authority("getBook", "获取书籍信息");
    Authority authorityRegisterBook = new Authority("registerBook", "登记书籍信息");
    authorities.saveAll(Set.of(authorityGetBook, authorityRegisterBook));

    Role roleUser = new Role(Roles.USER, "普通用户", "", Set.of(authorityGetBook));
    Role roleAdmin =
        new Role(Roles.ADMIN, "管理员", "", Set.of(authorityGetBook, authorityRegisterBook));
    roles.saveAll(Set.of(roleUser, roleAdmin));

    users.save(new User("user", "password", Set.of(roleUser)));
    users.save(new User("admin", "adminPassword", Set.of(roleUser, roleAdmin)));
  }
}
