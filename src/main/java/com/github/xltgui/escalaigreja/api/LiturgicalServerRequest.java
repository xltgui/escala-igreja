package com.github.xltgui.escalaigreja.api;

import com.github.xltgui.escalaigreja.model.liturgicalServers.LiturgicalServersRole;

public record LiturgicalServerRequest(
         String name,
         int age,
         LiturgicalServersRole role
) {
}
