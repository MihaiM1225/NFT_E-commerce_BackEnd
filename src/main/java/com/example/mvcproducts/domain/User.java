package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Long id;

  private String username;
  private String firstname;
  private String lastname;

  @OneToOne(mappedBy = "user")
  private Login login;

  @OneToMany(mappedBy = "user")
  private Set<Product> productSet = new HashSet<>();

  // instead of @OneToMany when having collection of basic types or enums
  @ElementCollection(fetch = FetchType.EAGER)
  private List<Role> roles=new ArrayList<>();

  public User(String username) {
    this.username = username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for(Role role : roles)
      authorities.add(new SimpleGrantedAuthority(role.toString()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
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
