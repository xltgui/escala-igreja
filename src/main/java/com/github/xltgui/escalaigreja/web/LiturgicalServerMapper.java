package com.github.xltgui.escalaigreja.web;

import com.github.xltgui.escalaigreja.api.LiturgicalServerResponse;
import com.github.xltgui.escalaigreja.model.liturgicalServers.LiturgicalServer;
import org.springframework.stereotype.Component;
@Component
public class LiturgicalServerMapper {
    public LiturgicalServerResponse toResponse(LiturgicalServer server) {
        return new LiturgicalServerResponse(server.getName(), server.getAge(), server.getRole());
    }
}
