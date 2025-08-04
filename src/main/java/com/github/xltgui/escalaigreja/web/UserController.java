package com.github.xltgui.escalaigreja.web;

import com.github.xltgui.escalaigreja.api.user.UserRequest;
import com.github.xltgui.escalaigreja.api.user.UserResponse;
import com.github.xltgui.escalaigreja.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(userService.save(mapper.toEntity(request))));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
