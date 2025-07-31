package com.github.xltgui.escalaigreja.api;

import com.github.xltgui.escalaigreja.model.liturgicalServers.LiturgicalServersRole;

public record LiturgicalServerResponse(
        String name,
        int age,
        LiturgicalServersRole role
) {

}
