package com.girevoy.site.service.impl;

import com.girevoy.site.domain.Post;
import com.girevoy.site.dto.PostDTO;
import com.girevoy.site.jpa.PostRepository;
import com.girevoy.site.service.api.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbPostService implements PostService {
    private final PostRepository postRepository;

    public DbPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDTO> findAll(String query) {
        List<Post> posts = query != null && !query.isBlank()
                           ? postRepository.findAllByTitleLike("%" + query + "%")
                           : postRepository.findAll();
        return posts.stream()
                    .map(post -> PostDTO.builder()
                                        .title(post.getTitle())
                                        .body(post.getBody())
                                        .img(post.getImg()).build())
                    .collect(Collectors.toList());
    }

//    @PostConstruct
//    public void setup() {
//        postRepository.saveAll(List.of(
//                Post.builder().title("First title from DB").body("First body. I am from DB").img("/img/1.jpg").build(),
//                Post.builder().title("Second title from DB").body("Second body. I am from DB").img("/img/2.jpg").build(),
//                Post.builder().title("Third title from DB").body("Third body. I am from DB").img("/img/3.jpg").build()));
//    }
}
