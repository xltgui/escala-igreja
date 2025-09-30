package com.github.xltgui.escalaigreja.web;

import com.github.xltgui.escalaigreja.api.user.UserRequest;
import com.github.xltgui.escalaigreja.api.user.UserResponse;
import com.github.xltgui.escalaigreja.domain.user.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserRequest userRequest);

    UserResponse toDto(UserEntity userEntity);

    List<UserResponse> toDtoList(List<UserEntity> userEntityList);
}
