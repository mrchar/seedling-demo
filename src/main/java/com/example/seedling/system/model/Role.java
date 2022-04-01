package com.example.seedling.system.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "alias")
  private String alias;

  @Column(name = "summary")
  private String summary;

  public Role() {}

  public Role(String name, String alias, String summary) {
    this.name = name;
    this.alias = alias;
    this.summary = summary;
  }

  @Override
  public String getAuthority() {
    return "ROLE_" + name;
  }
}
