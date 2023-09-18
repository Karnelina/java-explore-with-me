package ru.practicum.service;

import ru.practicum.model.Request;

import java.util.Collection;

public interface RequestService {
    Collection<Request> getRequestsToParticipateInOtherEvents(Long userId);

    Request saveUserRequest(Long userId, Long eventId);

    Request cancelOwnEvent(Long userId, Long requestId);
}
