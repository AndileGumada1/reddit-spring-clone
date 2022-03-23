package com.andile.springredditclone.persistance;

import com.andile.springredditclone.persistance.model.Comment;
import com.andile.springredditclone.persistance.model.Post;
import com.andile.springredditclone.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * @param post
     * @return
     */
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}