package com.github.xltgui.escalaigreja.api;

import com.github.xltgui.escalaigreja.domain.liturgicalServer.LiturgicalServersRole;

public record LiturgicalServerResponse(
        Long id,
        String name,
        int age,
        LiturgicalServersRole role
) {

}
