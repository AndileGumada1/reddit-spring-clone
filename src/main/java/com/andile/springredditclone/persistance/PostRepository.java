package com.andile.springredditclone.persistance;

import com.andile.springredditclone.persistance.model.Post;
import com.andile.springredditclone.persistance.model.Subreddit;
import com.andile.springredditclone.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}