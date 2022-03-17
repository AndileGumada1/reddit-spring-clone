package com.andile.springredditclone.persistance;

import com.andile.springredditclone.persistance.model.Post;
import com.andile.springredditclone.persistance.model.User;
import com.andile.springredditclone.persistance.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}