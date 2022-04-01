package com.example.seedling.system;

import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Entity
@Table(name = "user")
public class User implements UserDetails {
  private static final PasswordEncoder passwordEncoder =
      PasswordEncoderFactories.createDelegatingPasswordEncoder();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "`name`")
  private String name;

  @Column(name = "password")
  private String password;

  @PersistenceConstructor
  protected User() {}

  public User(String name) {
    this.name = name;
  }

  /**
   * 构造User实例
   *
   * <p>密码会经过编码后再进行持久化
   *
   * @param name 用户名
   * @param password 原始密码
   */
  public User(String name, String password) {
    this.name = name;
    this.password = passwordEncoder.encode(password);
  }

  public static User ofUsername(String username) {
    return new User(username);
  }

  /**
   * 设置用户密码
   *
   * <p>密码会经过编码后再进行持久化
   *
   * @param password 原始密码
   */
  public void setPassword(String password) {
    this.password = passwordEncoder.encode(password);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
