package com.github.xltgui.escalaigreja.web;

import com.github.xltgui.escalaigreja.api.login.LoginRequest;
import com.github.xltgui.escalaigreja.api.login.LoginResponse;
import com.github.xltgui.escalaigreja.domain.jwt.JwtService;
import com.github.xltgui.escalaigreja.domain.user.UserEntity;
import com.github.xltgui.escalaigreja.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {



    private final AuthenticationManager authenticationManager;
    private final JwtService tokenService;
    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<com.github.xltgui.escalaigreja.api.login.LoginResponse> login(@RequestBody LoginRequest request) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var username = auth.getName();
        UserEntity userEntity = userService.findByUsername(username);

        String token = tokenService.generateToken(userEntity);

        // 5. Retorna o Token JWT encapsulado no DTO de resposta.
        return ResponseEntity.ok(new LoginResponse(token, userMapper.toDtoLogin(userEntity)));
    }
}