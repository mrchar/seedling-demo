package com.example.seedling.system;

import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository users;

  public UserDetailsServiceImpl(UserRepository users) {
    this.users = users;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return users
        .findOne(Example.of(User.ofUsername(username)))
        .orElseThrow(
            () -> new UsernameNotFoundException(String.format("用户名:%s对应的用户不存在", username)));
  }
}
