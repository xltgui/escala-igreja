package com.github.xltgui.escalaigreja.domain.liturgicalServer;

import com.github.xltgui.escalaigreja.api.LiturgicalServerRequest;
import com.github.xltgui.escalaigreja.repository.LiturgicalServersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LiturgicalServerService {
    private final LiturgicalServersRepository repository;

    public LiturgicalServer create(LiturgicalServer server) {
        var createdServer = new LiturgicalServer(null, server.getName(), server.getAge(), server.getRole());
        return repository.save(createdServer);
    }

    public List<LiturgicalServer> list() {
        return repository.findAllByOrderByRoleAsc();
    }

    public void update(LiturgicalServer server) {
        if(server.getId() == null){
            throw new IllegalArgumentException("null id");
        }
        repository.save(server);
    }
}
