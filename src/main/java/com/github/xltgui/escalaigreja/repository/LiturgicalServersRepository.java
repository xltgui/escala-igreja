package com.github.xltgui.escalaigreja.repository;

import com.github.xltgui.escalaigreja.domain.liturgicalServer.LiturgicalServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiturgicalServersRepository  extends JpaRepository<LiturgicalServer, Long> {
    List<LiturgicalServer> findAllByOrderByRoleAsc();
}
