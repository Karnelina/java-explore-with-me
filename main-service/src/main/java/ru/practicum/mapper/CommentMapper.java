package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import ru.practicum.dto.comment.FullCommentDto;
import ru.practicum.model.Comment;

@Mapper(componentModel = "spring", uses = {UserMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CommentMapper {
    FullCommentDto toFullCommentDto(Comment comment);
}
