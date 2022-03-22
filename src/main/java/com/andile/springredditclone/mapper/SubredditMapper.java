package com.andile.springredditclone.mapper;

import com.andile.springredditclone.persistance.model.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.andile.springredditclone.api.dto.SubredditDto;
import com.andile.springredditclone.persistance.model.Subreddit;

import java.util.List;

/**
 *
 */
@Mapper(componentModel = "spring")
public interface SubredditMapper {

	/**
	 * @param subreddit
	 * @return
	 */
	@Mapping(target = "numberOfPosts",expression = "java(mapPosts(subreddit.getPosts()))")
	SubredditDto mapSubredditDto(Subreddit subreddit);

	/**
	 * @param numberOfPosts
	 * @return
	 */
	default Integer mapPosts(List<Post> numberOfPosts) {
		return numberOfPosts.size();
	}

	/**
	 * @param subreddit
	 * @return
	 */
	@InheritInverseConfiguration
	@Mapping(target = "posts", ignore = true)
	Subreddit mapDtoToSubreddit(SubredditDto subreddit);
}
