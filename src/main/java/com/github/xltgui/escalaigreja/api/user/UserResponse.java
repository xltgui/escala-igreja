package com.github.xltgui.escalaigreja.api.user;

import java.util.Set;

public record UserResponse(
        Long id,
        String username,
        String password,
        Set<String> roles
) {
}
