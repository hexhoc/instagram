package com.sm.socialmedia.mapper;

import com.sm.socialmedia.dto.post.PostCommand;
import com.sm.socialmedia.dto.post.PostQuery;
import com.sm.socialmedia.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "groupName", source = "group.name")
    @Mapping(target = "groupCreatorId", source = "group.creator.id")
    @Mapping(target = "groupCreatorUsername", source = "group.creator.username")
    @Mapping(target = "likes",
             expression = "java(entity.getPostAnalytic() == null ? 0 : entity.getPostAnalytic().getLikes())")
    PostQuery toDto(Post entity);

    Post toEntity(PostCommand dto);
}
