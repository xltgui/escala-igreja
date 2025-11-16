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

    public void update(LiturgicalServer server, Long id) {
        var existingServer = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Liturgical server not found with id: " + id));

        existingServer.setName(server.getName());
        existingServer.setAge(server.getAge());
        existingServer.setRole(server.getRole());

        repository.save(existingServer);
    }
}
