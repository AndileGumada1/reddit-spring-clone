package com.andile.springredditclone.api;

import com.andile.springredditclone.api.dto.SubredditDto;
import com.andile.springredditclone.api.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {
    private final SubredditService subredditService;

    /**End-point
     * @return
     */
    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        
       List<SubredditDto> list =subredditService.getAll();
       log.info("Request of list Subreddit :{}",list);
       
       return new ResponseEntity<List<SubredditDto>>(list,HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public SubredditDto getSubreddit(@PathVariable Long id) {
        log.info("get Subreddit: {}",id);
        return 
        		subredditService.getSubreddit(id);
    }

    /**
     * @param subredditDto
     * @return
     */
    @PostMapping
    public SubredditDto create(@RequestBody @Valid SubredditDto subredditDto) {
        log.info("Request for creating a reddit: {}",subredditDto);
        return subredditService.save(subredditDto);
    }
}
