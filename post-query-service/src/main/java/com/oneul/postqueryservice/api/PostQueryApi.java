package com.oneul.postqueryservice.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oneul.postqueryservice.domain.PostDocument;
import com.oneul.postqueryservice.service.PostQueryService;

@RestController
@RequestMapping(value = "/post")
public class PostQueryApi {
    private final PostQueryService postQueryService;
    
    public PostQueryApi(PostQueryService postQueryService){
        this.postQueryService = postQueryService;
    }

    @RequestMapping(value="", method=RequestMethod.GET)
    public Page<PostDocument> findAll(@RequestParam("page") Integer page) {
        Page<PostDocument> posts = postQueryService.findAll(PageRequest.of(page, 10));
        return posts;
    }

    @RequestMapping(value="/writer/{writerId}", method=RequestMethod.GET)
    public Page<PostDocument> findAllByWriter(@PathVariable Long writerId, @RequestParam("page") Integer page) {
        Page<PostDocument> posts = postQueryService.findByWriter(writerId, PageRequest.of(page, 10));
        return posts;
    }
}
