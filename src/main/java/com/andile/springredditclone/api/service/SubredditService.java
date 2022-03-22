package com.andile.springredditclone.api.service;

import com.andile.springredditclone.api.dto.SubredditDto;
import com.andile.springredditclone.exception.SpringRedditException;
import com.andile.springredditclone.mapper.SubredditMapper;
import com.andile.springredditclone.persistance.SubredditRepository;
import com.andile.springredditclone.persistance.model.Subreddit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 *
 */
@Service
@AllArgsConstructor
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    /**
     * @param subredditDto
     * @return
     */
    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    /**
     * @return
     */
    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditDto)
                .collect(toList());
    }

    /**
     * @param id
     * @return
     */
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubredditDto(subreddit);
    }
}
