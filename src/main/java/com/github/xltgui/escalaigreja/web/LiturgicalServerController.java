package com.github.xltgui.escalaigreja.web;

import com.github.xltgui.escalaigreja.api.LiturgicalServerRequest;
import com.github.xltgui.escalaigreja.api.LiturgicalServerResponse;
import com.github.xltgui.escalaigreja.model.liturgicalServers.LiturgicalServer;
import com.github.xltgui.escalaigreja.model.liturgicalServers.LiturgicalServersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/liturgical-servers")
public class LiturgicalServerController {
    private final LiturgicalServersService liturgicalServersService;
    private final LiturgicalServerMapper liturgicalServerMapper;

    @PostMapping
    public ResponseEntity<LiturgicalServerResponse> create(@RequestBody LiturgicalServerRequest request){
        var response = liturgicalServerMapper.toResponse(liturgicalServersService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
