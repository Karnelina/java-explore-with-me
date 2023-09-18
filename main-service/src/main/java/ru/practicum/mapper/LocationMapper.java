package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.location.LocationDtoCoordinates;
import ru.practicum.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toLocation(LocationDtoCoordinates locationDtoCoordinates);

    LocationDtoCoordinates toLocationDtoCoordinates(Location location);
}
