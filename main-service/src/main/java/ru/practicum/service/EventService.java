package ru.practicum.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.request.*;
import ru.practicum.model.enums.EventSort;
import ru.practicum.model.enums.EventStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public interface EventService {
    EventFullDto saveEvent(Long userId, NewEventDto newEventDto);

    Collection<EventShortDto> getEventsAddedByCurrentUser(Long userId, Pageable page);

    EventFullDto getEventAddedCurrentUser(Long userId, Long eventId);

    EventFullDto changeEventAddedCurrentUser(Long userId, Long eventId, UpdateEventUserRequestDto dto);

    Collection<ParticipationRequestDto> getRequestsByCurrentUser(Long userId, Long eventId);

    EventRequestStatusUpdateResultDto changeStatusOfRequestsByCurrentUser(Long userId, Long eventId,
                                                                          EventRequestStatusUpdateRequestDto dto);

    Collection<EventFullDto> getEventsByAdmin(Set<Long> userIds, Set<Long> categoryIds,
                                              Collection<EventStatus> states,
                                              LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                              Pageable pageable);

    EventFullDto updateEventByAdmin(Long eventId, UpdateEventAdminRequestDto dto);

    Collection<EventShortDto> getEventsPublic(String text, Set<Long> categoriesIds, Boolean paid,
                                              LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                              boolean onlyAvailable, EventSort sort, Pageable pageable,
                                              HttpServletRequest httpServletRequest);

    EventFullDto getEventByIdPublic(Long eventId, String uri, String ip);
}
