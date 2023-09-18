package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.model.Category;
import ru.practicum.model.Event;
import ru.practicum.model.Location;
import ru.practicum.model.User;
import ru.practicum.model.enums.EventStatus;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, LocationMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "location", source = "location")
    Event toEvent(NewEventDto newEventDto, Location location, Category category,
                  EventStatus state, User initiator);

    EventShortDto toEventShortDto(Event event);

    @Mapping(target = "id", source = "event.id")
    EventFullDto toEventFullDto(Event event, Category category, User initiator);

    EventFullDto toEventFullDto(Event event);
}
