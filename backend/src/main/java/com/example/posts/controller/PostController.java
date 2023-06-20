package com.example.posts.controller;

import com.example.posts.service.PostService;
import com.example.posts.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAll() {
        return postService.find();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable String id) {
        return postService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post body) {
        return postService.create(body);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable String id, @RequestBody Post body) {
        return postService.update(id, body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        postService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
