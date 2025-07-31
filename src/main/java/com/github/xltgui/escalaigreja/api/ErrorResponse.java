package com.github.xltgui.escalaigreja.api;

import java.util.List;

public record ErrorResponse(int status, String message, List<MyFieldError> fieldErrors) {
}
