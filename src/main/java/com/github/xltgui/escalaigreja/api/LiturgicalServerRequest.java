package com.github.xltgui.escalaigreja.api;

import com.github.xltgui.escalaigreja.domain.liturgicalServer.LiturgicalServersRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LiturgicalServerRequest(
        @NotBlank(message = "Field required!")
        String name,

        @NotNull
        @Positive
        int age,

        @NotNull(message = "Field required!")
        LiturgicalServersRole role
) {
}
