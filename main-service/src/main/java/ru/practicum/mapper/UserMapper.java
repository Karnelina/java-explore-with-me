package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.user.NewUserRequestDto;
import ru.practicum.dto.user.UserDto;
import ru.practicum.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(NewUserRequestDto newUserRequestDto);

    UserDto toUserDto(User user);
}
