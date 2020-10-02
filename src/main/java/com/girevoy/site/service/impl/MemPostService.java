package com.girevoy.site.service.impl;

import com.girevoy.site.dto.PostDTO;
import com.girevoy.site.service.api.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemPostService implements PostService {
    private List<PostDTO> posts = List.of(
            PostDTO.builder().title("First title").body("First body").img("/img/1.jpg").build(),
            PostDTO.builder().title("Second title").body("Second body").img("/img/2.jpg").build(),
            PostDTO.builder().title("Third title").body("Third body").img("/img/3.jpg").build());

    @Override
    public List<PostDTO> findAll(String query) {
        return query != null && !query.isEmpty()
                ? posts.stream()
                       .filter(post -> post.getTitle()
                                           .toLowerCase()
                                           .matches(".*" + query + ".*"))
                       .collect(Collectors.toList())
                : posts;
    }
}
