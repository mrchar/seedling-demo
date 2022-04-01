package com.example.seedling;

import com.example.seedling.book.Book;
import com.example.seedling.book.BookRepository;
import com.example.seedling.system.model.Role;
import com.example.seedling.system.model.Roles;
import com.example.seedling.system.model.User;
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

  public SeedlingApplication(BookRepository books, UserRepository users, RoleRepository roles) {
    this.books = books;
    this.users = users;
    this.roles = roles;
  }

  public static void main(String[] args) {
    SpringApplication.run(SeedlingApplication.class, args);
  }

  @PostConstruct
  public void initData() {
    books.save(new Book("title", "author", "description"));
    Role roleUser = new Role(Roles.USER, "普通用户", "");
    Role roleAdmin = new Role(Roles.ADMIN, "管理员", "");
    roles.saveAll(Set.of(roleUser, roleAdmin));
    users.save(new User("user", "password", Set.of(roleUser)));
    users.save(new User("admin", "adminPassword", Set.of(roleUser, roleAdmin)));
  }
}
