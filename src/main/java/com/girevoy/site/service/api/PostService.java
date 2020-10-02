package com.girevoy.site.service.api;

import com.girevoy.site.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> findAll(String query);
}
