package com.andile.springredditclone.mapper;

import com.andile.springredditclone.api.dto.CommentsDto;
import com.andile.springredditclone.persistance.model.Comment;
import com.andile.springredditclone.persistance.model.Post;
import com.andile.springredditclone.persistance.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {
    /**
     * @param commentsDto
     * @param post
     * @param user
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment map(CommentsDto commentsDto, Post post, User user);

    /**
     * @param comment
     * @return
     */
    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentsDto mapToDto(Comment comment);
}
