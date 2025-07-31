package com.github.xltgui.escalaigreja.repository;

import com.github.xltgui.escalaigreja.model.liturgicalServers.LiturgicalServer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiturgicalServersRepository  extends JpaRepository<LiturgicalServer, Long> {
}
