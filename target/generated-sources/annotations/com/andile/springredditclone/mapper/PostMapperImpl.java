package com.andile.springredditclone.mapper;

import com.andile.springredditclone.api.dto.PostRequest;
import com.andile.springredditclone.api.dto.PostResponse;
import com.andile.springredditclone.persistance.model.Post;
import com.andile.springredditclone.persistance.model.Post.PostBuilder;
import com.andile.springredditclone.persistance.model.Subreddit;
import com.andile.springredditclone.persistance.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-23T18:49:15+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_312 (Amazon.com Inc.)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public Post map(PostRequest postRequest, Subreddit subreddit, User user) {
        if ( postRequest == null && subreddit == null && user == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        if ( postRequest != null ) {
            post.description( postRequest.getDescription() );
            post.postId( postRequest.getPostId() );
            post.postName( postRequest.getPostName() );
            post.url( postRequest.getUrl() );
        }
        if ( subreddit != null ) {
            post.subreddit( subreddit );
        }
        if ( user != null ) {
            post.user( user );
        }
        post.createdDate( java.time.Instant.now() );
        post.voteCount( 0 );

        return post.build();
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setPostName( post.getPostName() );
        postResponse.setDescription( post.getDescription() );
        postResponse.setId( post.getPostId() );
        postResponse.setUserName( postUserUsername( post ) );
        postResponse.setUrl( post.getUrl() );
        postResponse.setSubredditName( postSubredditName( post ) );
        postResponse.setVoteCount( post.getVoteCount() );

        postResponse.setDuration( getDuration(post) );
        postResponse.setCommentCount( commentCount(post) );

        return postResponse;
    }

    private String postUserUsername(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String postSubredditName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subreddit subreddit = post.getSubreddit();
        if ( subreddit == null ) {
            return null;
        }
        String name = subreddit.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
