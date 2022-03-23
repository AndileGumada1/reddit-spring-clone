package com.andile.springredditclone.mapper;

import com.andile.springredditclone.api.dto.SubredditDto;
import com.andile.springredditclone.api.dto.SubredditDto.SubredditDtoBuilder;
import com.andile.springredditclone.persistance.model.Subreddit;
import com.andile.springredditclone.persistance.model.Subreddit.SubredditBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-23T18:49:15+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_312 (Amazon.com Inc.)"
)
@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubredditDto mapSubredditDto(Subreddit subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditDtoBuilder subredditDto = SubredditDto.builder();

        subredditDto.id( subreddit.getId() );
        subredditDto.name( subreddit.getName() );
        subredditDto.description( subreddit.getDescription() );

        subredditDto.numberOfPosts( mapPosts(subreddit.getPosts()) );

        return subredditDto.build();
    }

    @Override
    public Subreddit mapDtoToSubreddit(SubredditDto subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditBuilder subreddit1 = Subreddit.builder();

        subreddit1.id( subreddit.getId() );
        subreddit1.name( subreddit.getName() );
        subreddit1.description( subreddit.getDescription() );

        return subreddit1.build();
    }
}
