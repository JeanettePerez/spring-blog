package com.codeup.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserWithRoles extends User implements UserDetails {

  public UserWithRoles(User user) {
    super(user);
  }

  public UserWithRoles(User user, List<Object> emptyList) {
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    String roles = "";
    return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
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
