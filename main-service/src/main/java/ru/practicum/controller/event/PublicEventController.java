package ru.practicum.controller.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.aspect.ToLog;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.model.enums.EventSort;
import ru.practicum.service.EventService;
import ru.practicum.util.OffsetBasedPageRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import static ru.practicum.Constant.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
@ToLog
public class PublicEventController {
    private final EventService eventService;

    @GetMapping
    public Collection<EventShortDto> getEventsPublic(@RequestParam(required = false) String text,
                                                     @RequestParam(required = false) Set<Long> categoriesIds,
                                                     @RequestParam(required = false) Boolean paid,
                                                     @RequestParam(required = false)
                                                     @DateTimeFormat(pattern = TIME_PATTERN) LocalDateTime rangeStart,
                                                     @RequestParam(required = false)
                                                     @DateTimeFormat(pattern = TIME_PATTERN) LocalDateTime rangeEnd,
                                                     @RequestParam(defaultValue = "false") boolean onlyAvailable,
                                                     @RequestParam(required = false) EventSort sort,
                                                     @RequestParam(defaultValue = PAGE_DEFAULT_FROM)
                                                     @PositiveOrZero Integer from,
                                                     @RequestParam(defaultValue = PAGE_DEFAULT_SIZE)
                                                     @Positive Integer size,
                                                     HttpServletRequest request) {
        Pageable pageable = new OffsetBasedPageRequest(from, size);

        return eventService.getEventsPublic(text, categoriesIds, paid, rangeStart,
                rangeEnd, onlyAvailable, sort, pageable, request);
    }

    @GetMapping("/{id}")
    public EventFullDto getEventByIdPublic(@Positive @PathVariable Long id, HttpServletRequest request) {
        return eventService.getEventByIdPublic(id, request.getRequestURI(), request.getRemoteAddr());
    }
}
