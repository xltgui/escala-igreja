package com.github.xltgui.escalaigreja.domain.liturgicalServer;

import com.github.xltgui.escalaigreja.domain.schedule.Schedule;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LITURGICAL_SERVERS")
public class LiturgicalServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private LiturgicalServersRole role;

    public LiturgicalServer(Long id, String name, int age, LiturgicalServersRole role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.role = role;
    }

}
