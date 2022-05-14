package com.example.backend.dto.mapper;


import com.example.backend.dto.CommentDTO;
import com.example.backend.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentDTOToComment(CommentDTO commentDTO);

    CommentDTO commentToCommentDTO(Comment comment);
}