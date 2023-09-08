package ru.practicum.stats.mapper;

import org.mapstruct.Mapper;
import ru.practicum.stats.dto.EndpointHitRequestDto;
import ru.practicum.stats.dto.EndpointHitResponseDto;
import ru.practicum.stats.model.EndpointHit;

@Mapper(componentModel = "string")
public interface EndpointHitMapper {
    EndpointHit toEndpointHit(EndpointHitRequestDto endpointHitRequestDto);

    EndpointHitResponseDto toEndpointHitResponseDto(EndpointHit endpointHit);
}