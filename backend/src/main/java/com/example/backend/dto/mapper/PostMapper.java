package com.example.backend.dto.mapper;

import com.example.backend.dto.PostDTO;
import com.example.backend.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

//    @Mapping(source = "", target = "")
//    Post postDTOToPost(PostDTO postDTO);

    @Mapping(source = "user.username", target = "username")
    PostDTO postToPostDTO(Post post);
}