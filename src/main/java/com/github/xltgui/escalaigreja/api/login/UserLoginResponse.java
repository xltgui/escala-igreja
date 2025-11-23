package com.github.xltgui.escalaigreja.api.login;

import java.util.Set;

public record UserLoginResponse(
        String username,
        Set<String> roles
) {
}
