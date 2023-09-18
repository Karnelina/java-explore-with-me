package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.Request;
import ru.practicum.model.enums.RequestStatus;

import java.util.Collection;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByRequesterId(Long userId);

    Long countAllByEventIdAndStatus(Long eventId, RequestStatus status);

    List<Request> findAllByEventIdAndEventInitiatorId(Long eventId, Long UserId);

    List<Request> findAllByStatusAndEventIdIn(RequestStatus status, Collection<Long> eventIds);

    List<Request> findAllByEventIdAndEventInitiatorIdAndIdIn(Long eventId, Long userId,
                                                             Collection<Long> requestIds);
}
