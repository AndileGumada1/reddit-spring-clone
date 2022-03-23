package com.andile.springredditclone.api.service;

import com.andile.springredditclone.api.dto.PostRequest;
import com.andile.springredditclone.api.dto.PostResponse;
import com.andile.springredditclone.exception.PostNotFoundException;
import com.andile.springredditclone.exception.SubredditNotFoundException;
import com.andile.springredditclone.mapper.PostMapper;
import com.andile.springredditclone.persistance.PostRepository;
import com.andile.springredditclone.persistance.SubredditRepository;
import com.andile.springredditclone.persistance.UserRepository;
import com.andile.springredditclone.persistance.model.Post;
import com.andile.springredditclone.persistance.model.Subreddit;
import com.andile.springredditclone.persistance.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;


    /**
     * @param id
     * @return
     */
    public List<PostResponse> getPostsBySubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SubredditNotFoundException(id.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    /**
     * @param username
     * @return
     */
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    /**
     * @param id
     * @return
     */
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    /**
     * @return
     */
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    /**
     * @param postRequest
     */
    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }
}
