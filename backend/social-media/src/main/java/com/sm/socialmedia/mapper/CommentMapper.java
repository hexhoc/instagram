package com.sm.socialmedia.mapper;

import com.sm.socialmedia.dto.comment.CommentCommand;
import com.sm.socialmedia.dto.comment.CommentQuery;
import com.sm.socialmedia.dto.post.PostCommand;
import com.sm.socialmedia.dto.post.PostQuery;
import com.sm.socialmedia.entity.Comment;
import com.sm.socialmedia.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    CommentQuery toDto(Comment entity);

    Comment toEntity(CommentCommand dto);
}
