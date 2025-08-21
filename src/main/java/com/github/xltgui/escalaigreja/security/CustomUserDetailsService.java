package com.github.xltgui.escalaigreja.security;

import com.github.xltgui.escalaigreja.domain.user.UserEntity;
import com.github.xltgui.escalaigreja.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = service.findByUsername(username);
        return User.
                builder()
                .username(username)
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles().toArray(new String[userEntity.getRoles().size()]))
                .build();
    }
}
