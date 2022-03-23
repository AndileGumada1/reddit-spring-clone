package com.andile.springredditclone.mapper;

import com.andile.springredditclone.api.dto.CommentsDto;
import com.andile.springredditclone.persistance.model.Comment;
import com.andile.springredditclone.persistance.model.Comment.CommentBuilder;
import com.andile.springredditclone.persistance.model.Post;
import com.andile.springredditclone.persistance.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-23T18:49:15+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_312 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentsDto commentsDto, Post post, User user) {
        if ( commentsDto == null && post == null && user == null ) {
            return null;
        }

        CommentBuilder comment = Comment.builder();

        if ( commentsDto != null ) {
            comment.text( commentsDto.getText() );
        }
        if ( post != null ) {
            comment.post( post );
            comment.user( post.getUser() );
        }
        comment.createdDate( java.time.Instant.now() );

        return comment.build();
    }

    @Override
    public CommentsDto mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setId( comment.getId() );
        commentsDto.setCreatedDate( comment.getCreatedDate() );
        commentsDto.setText( comment.getText() );

        commentsDto.setUserName( comment.getUser().getUsername() );
        commentsDto.setPostId( comment.getPost().getPostId() );

        return commentsDto;
    }
}
