package com.github.xltgui.escalaigreja.domain.user;

import com.github.xltgui.escalaigreja.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository repository;
    private final PasswordEncoder encoder;

    public UserEntity save(UserEntity userEntity) {
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        return repository.save(userEntity);
    }

    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this email"));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID"));
        repository.deleteById(id);
    }
}
