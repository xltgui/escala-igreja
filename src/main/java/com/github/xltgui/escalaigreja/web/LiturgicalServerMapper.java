package com.github.xltgui.escalaigreja.web;

import com.github.xltgui.escalaigreja.api.LiturgicalServerRequest;
import com.github.xltgui.escalaigreja.api.LiturgicalServerResponse;
import com.github.xltgui.escalaigreja.domain.liturgicalServer.LiturgicalServer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LiturgicalServerMapper {

    @Mapping(target = "id", ignore = true)
    LiturgicalServer toEntity(LiturgicalServerRequest dto);

    LiturgicalServerResponse toDto(LiturgicalServer entity);

    List<LiturgicalServerResponse> toDtoList(List<LiturgicalServer> entityList);
}
