package ru.practicum.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.aspect.ToLog;
import ru.practicum.dto.user.NewUserRequestDto;
import ru.practicum.dto.user.UserDto;
import ru.practicum.mapper.UserMapper;
import ru.practicum.model.User;
import ru.practicum.service.UserService;
import ru.practicum.util.OffsetBasedPageRequest;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.practicum.Constant.PAGE_DEFAULT_FROM;
import static ru.practicum.Constant.PAGE_DEFAULT_SIZE;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
@ToLog
public class AdminUserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public Collection<UserDto> getUsers(@RequestParam(required = false) Set<Long> ids,
                                        @RequestParam(defaultValue = PAGE_DEFAULT_FROM) @PositiveOrZero Integer from,
                                        @RequestParam(defaultValue = PAGE_DEFAULT_SIZE) @Positive Integer size) {
        Pageable page = new OffsetBasedPageRequest(from, size);
        return userService.getUsersByIds(ids, page)
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@Valid @RequestBody NewUserRequestDto newUserRequestDto) {
        User user = userService.saveUser(userMapper.toUser(newUserRequestDto));
        return userMapper.toUserDto(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@Positive @PathVariable Long userId) {
        userService.deleteById(userId);
    }
}
