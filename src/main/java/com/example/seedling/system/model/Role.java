package com.example.seedling.system.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

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

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "role_authority",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id"))
  Set<Authority> authorities;

  public Role() {}

  public Role(String name, String alias, String summary) {
    this.name = name;
    this.alias = alias;
    this.summary = summary;
  }

  public Role(String name, String alias, String summary, Set<Authority> authorities) {
    this(name, alias, summary);
    this.authorities = authorities;
  }

  @Override
  public String getAuthority() {
    return "ROLE_" + name;
  }
}
