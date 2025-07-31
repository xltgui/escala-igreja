package com.github.xltgui.escalaigreja.model.liturgicalServers;

import com.github.xltgui.escalaigreja.api.LiturgicalServerRequest;
import com.github.xltgui.escalaigreja.repository.LiturgicalServersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiturgicalServersService {
    private final LiturgicalServersRepository repository;

    public LiturgicalServer create(LiturgicalServerRequest request) {
        var createdServer = new LiturgicalServer(null, request.name(), request.age(), request.role());
        return repository.save(createdServer);
    }



}
