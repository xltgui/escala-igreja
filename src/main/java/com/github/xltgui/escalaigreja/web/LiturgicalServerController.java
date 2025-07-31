package com.github.xltgui.escalaigreja.web;

import com.github.xltgui.escalaigreja.api.LiturgicalServerRequest;
import com.github.xltgui.escalaigreja.api.LiturgicalServerResponse;
import com.github.xltgui.escalaigreja.domain.liturgicalServer.LiturgicalServerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/liturgical-servers")
public class LiturgicalServerController {
    private final LiturgicalServerService liturgicalServerService;
    private final LiturgicalServerMapper mapper;

    @PostMapping
    public ResponseEntity<LiturgicalServerResponse> create(@Valid @RequestBody LiturgicalServerRequest request){
        var response = mapper.toDto(liturgicalServerService.create(mapper.toEntity(request)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LiturgicalServerResponse>> list(){
        return ResponseEntity.ok(mapper.toDtoList(liturgicalServerService.list()));
    }

    @PutMapping({"{id}"})
    public ResponseEntity<?> update(@Valid @RequestBody LiturgicalServerRequest request, @PathVariable Long id){
        liturgicalServerService.update(mapper.toEntity(request));
        return ResponseEntity.noContent().build();
    }

}
