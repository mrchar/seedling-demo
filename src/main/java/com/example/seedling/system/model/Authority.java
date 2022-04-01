package com.example.seedling.system.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "summary")
  private String summary;

  public Authority() {}

  public Authority(String name, String summary) {
    this.name = name;
    this.summary = summary;
  }

  @Override
  public String getAuthority() {
    return name;
  }
}
