package com.example.basicauthentication4.service;

import com.example.basicauthentication4.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetail implements UserDetails {

    private String username;

    private String password;

    private List<GrantedAuthority> roles; // rollerin tutulduğu liste.

    public CustomUserDetail(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {  // Kullanıcının hesabının süresinin dolup dolmadığını gösterir. Süresi dolmuş bir hesabın kimliği doğrulanamaz.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  // Kullanıcının kilitli mi yoksa kilidi açık mı olduğunu gösterir. Kilitli bir kullanıcının kimliği doğrulanamaz.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // Kullanıcının kimlik bilgilerinin (parola) süresinin dolup dolmadığını gösterir. Süresi dolmuş kimlik bilgileri, kimlik doğrulamasını engeller.
        return true;
    }

    @Override
    public boolean isEnabled() { // Kullanıcının etkin mi yoksa devre dışı mı olduğunu gösterir. Devre dışı bırakılmış bir kullanıcının kimliği doğrulanamaz.
        return true;
    }
}
