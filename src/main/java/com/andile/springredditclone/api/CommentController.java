package com.andile.springredditclone.api;

import com.andile.springredditclone.api.dto.CommentsDto;
import com.andile.springredditclone.api.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/comments/")
public class CommentController {

    private final CommentService commentService;
    private ObjectMapper objectMapper;

    /**
     * @param commentsDto
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) throws JsonProcessingException {
        log.info("Comment Request: {}",objectMapper.writeValueAsString(commentsDto));
        commentService.createComment(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    /**
     * @param postId
     * @return
     */
    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@RequestParam("postId") Long postId) {
        log.info("Request for Comment for post: {}",postId);
        return status(OK)
                .body(commentService.getCommentByPost(postId));
    }

    /**
     * @param userName
     * @return
     */
    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsByUser(@RequestParam("userName") String userName) {
        log.info("Request for Comment for username: {}",userName);
        return status(OK).body(commentService.getCommentsByUser(userName));
    }
}
